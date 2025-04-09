package dev.angelzhang.mediaservice.clients;

import dev.angelzhang.mediaservice.dto.details.DetailsAPIRequest;
import dev.angelzhang.mediaservice.dto.searchMulti.SearchMultiAPIRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface TMDBClient {

    @GetExchange("/search/multi")
    SearchMultiAPIRequest searchMulti(@RequestParam String query,
                                      @RequestParam(name = "include_adult") Boolean includeAdult,
                                      @RequestParam String language,
                                      @RequestParam Integer page);

    @GetExchange("/tv/{id}")
    DetailsAPIRequest getTVDetails(@PathVariable Integer id);

    @GetExchange("/movie/{id}")
    DetailsAPIRequest getMovieDetails(@PathVariable Integer id);

    @GetExchange("/trending/{type}/{timeWindow}")
    SearchMultiAPIRequest trending(@PathVariable String type,
                                   @PathVariable String timeWindow);
}
