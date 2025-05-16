package com.tevfikkoseli.WebClientDemo.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
public class WebClientConfig {

    @Value("${baseUrl}")
    private String baseUrl;

    @Bean
    public WebClient getWebClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders( header ->
                        {
                            header.setContentType(MediaType.APPLICATION_JSON );
                            header.setAccept(List.of(MediaType.APPLICATION_JSON));
                        }
                ).build();
    }
}
