package com.taskflow.api_gateway.config;

import com.taskflow.api_gateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Value("${app.services.user-service.url}")
    private String userServiceUrl;

    @Value("${app.services.project-service.url}")
    private String projectServiceUrl;

    @Value("${app.services.task-service.url}")
    private String taskServiceUrl;

    @Value("${app.services.notification-service.url}")
    private String notificationServiceUrl;

    @Autowired
    public GatewayConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Health check route
                .route("health-check", r -> r
                        .path("/health")
                        .uri("forward:/actuator/health")
                )

                // User Service Routes - Authentication endpoints (no JWT required)
                .route("user-service-auth", r -> r
                        .path("/api/auth/**")
                        .uri(userServiceUrl)
                )

                // User Service Routes - Protected endpoints
                .route("user-service", r -> r
                        .path("/api/users/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri(userServiceUrl)
                )

                // Project Service Routes
                .route("project-service", r -> r
                        .path("/api/projects/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri(projectServiceUrl)
                )

                // Task Service Routes
                .route("task-service", r -> r
                        .path("/api/tasks/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri(taskServiceUrl)
                )

                // Notification Service Routes
                .route("notification-service", r -> r
                        .path("/api/notifications/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri(notificationServiceUrl)
                )
                .build();
    }
}
