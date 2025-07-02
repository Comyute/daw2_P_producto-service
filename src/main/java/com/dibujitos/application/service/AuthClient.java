package com.dibujitos.application.service;

import com.dibujitos.web.dto.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "auth-service", url = "http://localhost:8080")
public interface AuthClient {
    @PostMapping("/api/token")
    TokenResponse getToken(@RequestBody Map<String, String> credentials);
}
