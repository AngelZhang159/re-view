package dev.angelzhang.reviewservice.clients;

import dev.angelzhang.reviewservice.dto.media.details.DetailsAPIResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;

public interface MediaClient {

    @GetExchange("/details/{type}/{id}")
    DetailsAPIResponse details(@RequestHeader(value = "Authorization") String token, @PathVariable String type, @PathVariable Integer id);

}
