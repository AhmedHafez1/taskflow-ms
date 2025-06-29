package com.taskflow.api_gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import reactor.core.publisher.Mono;

public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Log request details
            var startTime = System.currentTimeMillis();
            logger.info("Request: {} {} from {}",
                    exchange.getRequest().getMethod(),
                    exchange.getRequest().getURI(),
                    exchange.getRequest().getRemoteAddress());
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // Log response details
                var endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                logger.info("Response: {} {} - Status {} in {} ms",
                        exchange.getRequest().getMethod(),
                        exchange.getRequest().getURI(),
                        exchange.getResponse().getStatusCode(),
                        duration);
                System.out.println("Response: " + exchange.getResponse().getStatusCode());
            }));
        };
    }

    public static class Config {
        // Configuration properties can be added here if needed
    }
}
