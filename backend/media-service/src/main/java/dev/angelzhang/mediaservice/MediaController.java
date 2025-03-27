package dev.angelzhang.mediaservice;

import dev.angelzhang.mediaservice.dto.searchMulti.SearchMultiAPIRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/media/")
@Slf4j
public class MediaController {

    private final MediaService mediaService;

    //search/multi?query=PARAM&include_adult=false&language=en-US&page=1
    @GetMapping("/search/multi")
    public ResponseEntity<SearchMultiAPIRequest> search(
            @RequestParam String query,
            @RequestParam(required = false, defaultValue = "false", name = "include_adult") Boolean includeAdult,
            @RequestParam(required = false, defaultValue = "en-US") String language,
            @RequestParam(required = false, defaultValue = "1") Integer page) {
        log.info("New movie search query: {}", query);
        return mediaService.search(query, includeAdult, language, page);
    }

    @GetMapping("/details/{type}/{id}")
    public ResponseEntity<?> mediaDetails(@PathVariable String type, @PathVariable Integer id) {
        log.info("New movie details query with type: {}, id: {}", type, id);
        return mediaService.getMediaDetailsById(type, id);
    }

    @GetMapping("/health")
    public String health() {
        return "API WORKS";
    }

}
