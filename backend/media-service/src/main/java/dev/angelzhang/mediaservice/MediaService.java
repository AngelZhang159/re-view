package dev.angelzhang.mediaservice;

import dev.angelzhang.mediaservice.dto.details.DetailsAPIRequest;
import dev.angelzhang.mediaservice.dto.details.DetailsAPIResponse;
import dev.angelzhang.mediaservice.dto.searchMulti.SearchMultiAPIBody;
import dev.angelzhang.mediaservice.dto.searchMulti.SearchMultiAPIRequest;
import dev.angelzhang.mediaservice.entities.details.MovieDetails;
import dev.angelzhang.mediaservice.entities.details.TVDetails;
import dev.angelzhang.mediaservice.repositories.MovieDetailsRepository;
import dev.angelzhang.mediaservice.repositories.TVDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Optional;

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

    public ResponseEntity<?> getMediaDetailsById(String type, Integer id) {
        return switch (type) {
            case "movie" -> findMovieById(id);
            case "tv" -> findTVById(id);
//            case "person" -> ResponseEntity.badRequest();
            default -> ResponseEntity.badRequest().build();
        };
    }

    private ResponseEntity<?> findTVById(Integer id) {
        Optional<TVDetails> byId = tvDetailsRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(DetailsAPIResponse.toTVDetailsResponse(byId.get()));
        } else {
            return ResponseEntity.ok(fetchAndSaveTVDetails(id));
        }
    }

    private DetailsAPIResponse fetchAndSaveTVDetails(Integer id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/tv/" + id).build())
                .retrieve()
                .bodyToMono(DetailsAPIRequest.class)
                .map(TVDetails::fromRequest)
                .doOnNext(tvDetailsRepository::save)
                .map(DetailsAPIResponse::toTVDetailsResponse)
                .block();
    }

    private ResponseEntity<?> findMovieById(Integer id) {
        Optional<MovieDetails> byId = movieDetailsRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(DetailsAPIResponse.toMovieDetailsResponse(byId.get()));
        } else {
            return ResponseEntity.ok(fetchAndSaveMovieDetails(id));
        }
    }

    private DetailsAPIResponse fetchAndSaveMovieDetails(Integer id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/movie/" + id).build())
                .retrieve()
                .bodyToMono(DetailsAPIRequest.class)
                .map(MovieDetails::fromRequest)
                .doOnNext(movieDetailsRepository::save)
                .map(DetailsAPIResponse::toMovieDetailsResponse)
                .block();
    }

}
