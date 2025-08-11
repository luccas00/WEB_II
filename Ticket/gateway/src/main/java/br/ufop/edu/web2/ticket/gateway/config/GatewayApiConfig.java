package br.ufop.edu.web2.ticket.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

@Configuration
public class GatewayApiConfig {

    @Value("${gateway.frontend.uri}")
    private String uriFrontendService = "http://localhost:1234";

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("home",
                        p -> p.path("/")
                                .filters(f -> f.rewritePath("/", "/"))
                                .uri("lb://users-service")
                )
                .route("users-api",
                        p -> p.path("/api/users")
                                .filters(f -> f.rewritePath("/api/users", "/users"))
                                .uri("lb://users-service")
                )
                .route("users",
                        p -> p.path("/users")
                                .uri("lb://users-service")
                )
                .route("users-segment",
                        p -> p.path("/users/**")
                                .uri("lb://users-service")
                )
                .route("sales-api",
                        p -> p.path("/api/sales")
                                .filters(f -> f.rewritePath("/api/sales", "/sales"))
                                .uri("lb://sales")
                )
                .route("sales",
                        p -> p.path("/sales/**")
                                .uri("lb://sales")
                )
                .route("events",
                        p -> p.path("/events/**")
                                .uri("lb://sales")
                )
                .route("notifications-api",
                        p -> p.path("/api/notifications")
                                .filters(f -> f.rewritePath("/api/notifications", "/notifications"))
                                .uri("lb://notifications")
                )
                .route("notifications",
                        p -> p.path("/notifications/**")
                                .uri("lb://notifications")
                )
                .route("notifications", r -> r
                        .path("/api/notifications/**")
                        .filters(f -> f.rewritePath(
                                "/api/notifications(?<segment>/?.*)", "/notifications${segment}"
                        ))
                        .uri("lb://notifications-service")
                )
                .route("favicon", r -> r
                        .path("/favicon.ico")
                        .filters(f -> f.setStatus(HttpStatus.NO_CONTENT))
                        .uri("no://op")
                )
                .route("frontend",
                        p -> p.path("/**")
                                .uri(this.uriFrontendService)
                )
                .build();

    }

}


//
//@Configuration
//public class GatewayApiConfig {
//
//    @Value("${gateway.frontend.uri}")
//    private String uriFrontendService = "http://localhost:5173";
//
//    @Bean
//    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
//
//        return builder.routes()
//                .route("users-api",
//                        p -> p.path("/api/users")
//                                .filters(f -> f.rewritePath("/api/users", "/users"))
//                                .uri("lb://users-service")
//                )
//                .route("users",
//                        p -> p.path("/users")
//                                .uri("lb://users-service")
//                )
//                .route("users-segment",
//                        p -> p.path("/users/**")
//                                .uri("lb://users-service")
//                )
//                .route("sales",
//                        p -> p.path("/sales/**")
//                                .uri("lb://sales-service")
//                )
//                .route("notifications",
//                        p -> p.path("/notifications/**")
//                                .uri("lb://notifications-service")
//                )
//                .route("frontend",
//                        p -> p.path("/**")
//                                .uri(this.uriFrontendService)
//                )
//                .build();
//
//    }
//
//}
