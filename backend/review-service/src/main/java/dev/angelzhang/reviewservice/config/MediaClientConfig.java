package dev.angelzhang.reviewservice.config;

import dev.angelzhang.reviewservice.clients.MediaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class MediaClientConfig {

    @Bean
    MediaClient mediaClient() {
        String mediaServiceUrl = "http://localhost:8001/media";
        RestClient client = RestClient.create(mediaServiceUrl);

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client)).build();

        return factory.createClient(MediaClient.class);
    }

}
