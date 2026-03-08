package com.infraseed.webcloud.user.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Placeholder controller. Replace with real user/tenant/OAuth and mobile auth-code endpoints.
 */
@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "User management, OAuth, mobile auth")
public class UserController {

    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Current user")
    public Mono<Map<String, String>> me() {
        return Mono.just(Map.of("message", "user-service placeholder", "oauth", "github/weixin placeholders", "mobile", "authcode signup/login placeholders"));
    }
}
