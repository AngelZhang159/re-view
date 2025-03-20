package dev.angelzhang.mediaservice;

import dev.angelzhang.mediaservice.DTO.SearchMultiAPIBody;
import dev.angelzhang.mediaservice.DTO.SearchMultiAPIRequest;
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
}
