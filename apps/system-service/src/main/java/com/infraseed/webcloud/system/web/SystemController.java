package com.infraseed.webcloud.system.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Aggregated OpenAPI / service catalog placeholder. Replace with actual aggregation of user/data service OpenAPI specs.
 */
@RestController
@RequestMapping("/api/system")
public class SystemController {

    @Value("${spring.application.name:system-service}")
    private String appName;

    @GetMapping(value = "/docs", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> docs() {
        return Map.of(
                "title", "Aggregated API Documentation",
                "message", "Aggregate OpenAPI from user-service and data-service",
                "services", List.of("user-service", "data-service")
        );
    }

    @GetMapping(value = "/catalog", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> catalog() {
        return Map.of(
                "application", appName,
                "services", List.of(
                        Map.of("id", "user-service", "path", "/api/user"),
                        Map.of("id", "data-service", "path", "/api/data")
                )
        );
    }
}
