package com.tevfikkoseli.WebClientDemo.infrastructure;

import com.tevfikkoseli.WebClientDemo.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;


@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurity {


    @Bean
    SecurityWebFilterChain securityWebFilterChain (ServerHttpSecurity http,
                                                   JwtService jwtService) {

        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .addFilterAt(new JwtAuthenticationFilter(jwtService), SecurityWebFiltersOrder.AUTHENTICATION)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .build();
    }
}
