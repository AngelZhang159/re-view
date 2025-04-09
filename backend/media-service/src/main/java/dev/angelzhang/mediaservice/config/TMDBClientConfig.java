package dev.angelzhang.mediaservice.config;

import dev.angelzhang.mediaservice.clients.TMDBClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Component
public class TMDBClientConfig {

    @Value("${themoviedb.api.base-url}")
    private String baseUrl;

    @Value("${themoviedb.api.token}")
    private String token;

    @Bean
    TMDBClient TMDBClient() {
        RestClient client = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Authorization", "Bearer " + token)
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client)).build();

        return factory.createClient(TMDBClient.class);
    }
}
