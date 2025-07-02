package com.dibujitos.web.dto;

public record TokenResponse(String token, String typeToken, Long expiresInSeconds) {
}
