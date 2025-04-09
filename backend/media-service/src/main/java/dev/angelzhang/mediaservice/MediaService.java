package dev.angelzhang.mediaservice;

import dev.angelzhang.mediaservice.clients.TMDBClient;
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

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class MediaService {

    private final TMDBClient tmdbClient;
    private final MovieDetailsRepository movieDetailsRepository;
    private final TVDetailsRepository tvDetailsRepository;

    public ResponseEntity<SearchMultiAPIRequest> search(String query, Boolean includeAdult, String language, Integer page) {

        SearchMultiAPIRequest searchMultiAPIRequest = tmdbClient.searchMulti(query, includeAdult, language, page);
        searchMultiAPIRequest.results().sort(
                Comparator
                        .comparing((SearchMultiAPIBody body) -> body.posterPath() != null ? 0 : 1)
                        .thenComparing(SearchMultiAPIBody::voteCount, Comparator.nullsLast(Comparator.reverseOrder()))
        );

        return ResponseEntity.ok(searchMultiAPIRequest);
    }

    public ResponseEntity<?> getMediaDetailsById(String type, Long id) {
        return switch (type) {
            case "movie" -> findMovieById(id);
            case "tv" -> findTVById(id);
            default -> ResponseEntity.badRequest().build();
        };
    }

    private ResponseEntity<?> findTVById(Long id) {
        Optional<TVDetails> byId = tvDetailsRepository.findById(id);
        return byId
                .map(
                        tvDetails -> ResponseEntity
                                .ok(
                                        DetailsAPIResponse
                                                .toTVDTO(tvDetails)))
                .orElseGet(
                        () -> ResponseEntity
                                .ok(fetchAndSaveTVDetails(id)));
    }

    private DetailsAPIResponse fetchAndSaveTVDetails(Long id) {
        DetailsAPIRequest tvDetails = tmdbClient.getTVDetails(id);

        TVDetails tvDetails1 = TVDetails.toEntity(tvDetails);

        tvDetailsRepository.save(tvDetails1);

        return DetailsAPIResponse.toTVDTO(tvDetails1);
    }

    private ResponseEntity<?> findMovieById(Long id) {
        return movieDetailsRepository.findById(id)
                .map(movieDetails -> ResponseEntity.ok(
                        DetailsAPIResponse.toMovieDTO(movieDetails)))
                .orElseGet(() -> ResponseEntity.ok(
                        fetchAndSaveMovieDetails(id)));
    }

    private DetailsAPIResponse fetchAndSaveMovieDetails(Long id) {
        DetailsAPIRequest movieDetails = tmdbClient.getMovieDetails(id);

        MovieDetails movieDetails1 = MovieDetails.toEntity(movieDetails);

        movieDetailsRepository.save(movieDetails1);

        return DetailsAPIResponse.toMovieDTO(movieDetails1);
    }

    public ResponseEntity<SearchMultiAPIRequest> trending(String type, String typeWindow) {
        //TODO cache results maybe
        SearchMultiAPIRequest searchMultiAPIRequest = tmdbClient.trending(type, typeWindow);

        return ResponseEntity.ok(searchMultiAPIRequest);
    }
}
