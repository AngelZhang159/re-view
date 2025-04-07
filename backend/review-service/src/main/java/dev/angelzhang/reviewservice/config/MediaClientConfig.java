package dev.angelzhang.reviewservice.config;

import dev.angelzhang.reviewservice.clients.MediaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class MediaClientConfig {

    @Value(value = "${mediaservice.url}")
    private String mediaUrl;

    @Bean
    MediaClient mediaClient() {
        String mediaServiceUrl = mediaUrl;
        RestClient client = RestClient.create(mediaServiceUrl);

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client)).build();

        return factory.createClient(MediaClient.class);
    }

}
