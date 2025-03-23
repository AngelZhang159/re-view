package dev.angelzhang.mediaservice;

import dev.angelzhang.mediaservice.dto.details.DetailsAPIRequest;
import dev.angelzhang.mediaservice.dto.searchMulti.SearchMultiAPIBody;
import dev.angelzhang.mediaservice.dto.searchMulti.SearchMultiAPIRequest;
import dev.angelzhang.mediaservice.entities.details.MovieDetails;
import dev.angelzhang.mediaservice.entities.details.TVDetails;
import dev.angelzhang.mediaservice.repositories.MovieDetailsRepository;
import dev.angelzhang.mediaservice.repositories.TVDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
@Slf4j
public class MediaService {

    private final WebClient webClient;
    private final MovieDetailsRepository movieDetailsRepository;
    private final TVDetailsRepository tvDetailsRepository;

    public Mono<SearchMultiAPIRequest> search(String query, Boolean includeAdult, String language, Integer page) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/multi")
                        .queryParam("query", query)
                        .queryParam("include_adult", includeAdult)
                        .queryParam("language", language)
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .bodyToMono(SearchMultiAPIRequest.class)
                .flatMap(response -> {
                    response.results().sort(
                            Comparator
                                    .comparing((SearchMultiAPIBody body) -> body.poster_path() != null ? 0 : 1)
                                    .thenComparing(SearchMultiAPIBody::vote_count, Comparator.nullsLast(Comparator.reverseOrder()))
                    );
                    return Mono.just(response);
                });
    }

    public Mono<?> getMediaDetailsById(String type, Integer id) {
        return switch (type) {
            case "movie" -> findMovieById(id).switchIfEmpty(fetchAndSaveMovieDetails(id));
            case "tv" -> findTVById(id).switchIfEmpty(fetchAndSaveTVDetails(id));
            case "person" -> Mono.just("Person details not supported yet");
            default -> fetchDetailsFromAPI(type, id);
        };
    }

    private Mono<MovieDetails> findMovieById(Integer id) {
        return Mono.justOrEmpty(movieDetailsRepository.findById(id));
    }

    private Mono<TVDetails> findTVById(Integer id) {
        return Mono.justOrEmpty(tvDetailsRepository.findById(id));
    }

    private Mono<MovieDetails> fetchAndSaveMovieDetails(Integer id) {
        return fetchDetailsFromAPI("movie", id)
                .map(details -> (MovieDetails) details)
                .doOnNext(movieDetailsRepository::save);
    }

    private Mono<TVDetails> fetchAndSaveTVDetails(Integer id) {
        return fetchDetailsFromAPI("tv", id)
                .map(details -> (TVDetails) details)
                .doOnNext(tvDetailsRepository::save);
    }

    private Mono<?> fetchDetailsFromAPI(String type, Integer id) {
        String path = getPathForAPI(type, id);
        if (path == null) {
            return Mono.empty();
        }

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(path).build())
                .retrieve()
                .bodyToMono(DetailsAPIRequest.class)
                .flatMap(request -> mapDetailsFromRequest(type, request));
    }

    private String getPathForAPI(String type, Integer id) {
        return switch (type) {
            case "movie", "tv" -> "/" + type + "/" + id;
            default -> {
                log.error("Invalid type: {}", type);
                yield null;
            }
        };
    }

    private Mono<?> mapDetailsFromRequest(String type, DetailsAPIRequest request) {
        Object details = switch (type) {
            case "movie" -> {
                MovieDetails movieDetails = MovieDetails.fromRequest(request);
                movieDetailsRepository.save(movieDetails);
                yield movieDetails;
            }
            case "tv" -> {
                TVDetails tvDetails = TVDetails.fromRequest(request);
                tvDetailsRepository.save(tvDetails);
                yield tvDetails;
            }
            case "person" -> {
                log.info("Person details not supported yet");
                yield null;
            }
            default -> {
                log.error("Invalid type for mapping: {}", type);
                yield null;
            }
        };

        return details != null ? Mono.just(details) : Mono.empty();
    }



}
