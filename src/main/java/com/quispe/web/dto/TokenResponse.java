package com.quispe.web.dto;

public record TokenResponse(String token, String typeToken, Long expiresInSeconds) {
}
