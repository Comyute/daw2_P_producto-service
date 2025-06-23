package com.quispe.application.service;

import com.quispe.web.dto.DoctorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "quispe-t2-doctores-service", url = "http://localhost:8082")
public interface DoctorClient {
    @GetMapping("/api/doctores/by-cita/{citaId}")
    DoctorDto getDoctorByCita(@PathVariable Long citaId
                                    ,@RequestHeader("Authorization") String authorizationHeader
    );
}
