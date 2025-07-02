package com.dibujitos.application.service;

import com.dibujitos.web.dto.CategoriaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "categoria-service", url = "http://localhost:8081")
public interface CategoriaClient {
    @GetMapping("/api/categoria/{categoriaId}")
    CategoriaDto getCategoriaById(@PathVariable Long categoriaId
                                ,@RequestHeader("Authorization") String authorizationHeader
    );
}
