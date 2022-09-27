package com.gihanz.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class CustomFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        log.info("Custom Filtter............................");
        HttpHeaders headers = request.getHeaders();
        String autharization = request.getHeaders().getFirst("Autharization");
        String contentType = request.getHeaders().getFirst("Content-Type");
        log.info("Autharization : "+autharization);
        log.info("Content-Type : "+contentType);
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            log.info("Http Response : "+exchange.getResponse().getStatusCode());
        }));
    }
}
