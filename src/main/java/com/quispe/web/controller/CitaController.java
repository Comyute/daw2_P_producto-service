package com.quispe.web.controller;

import com.quispe.application.service.CitaService;
import com.quispe.web.dto.CitaRequestDto;
import com.quispe.web.dto.CitaResponseDto;
import com.quispe.web.dto.DoctorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService service;

    @PostMapping
    public ResponseEntity<CitaResponseDto> crear(@RequestBody CitaRequestDto request){
        return ResponseEntity.ok(service.crearCita(request));
    }

    @PutMapping("{id}/estado")
    public ResponseEntity<CitaResponseDto> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String nuevoEstado
    ){
        return ResponseEntity.ok(service.actualizarEstado(id, nuevoEstado));
    }

    @GetMapping
    public ResponseEntity<List<CitaResponseDto>> listar() {
        return ResponseEntity.ok(service.listarCitas());
    }

    @GetMapping("/id/{cita-id}")
    public ResponseEntity<CitaResponseDto> listaPorId(@PathVariable Long citaId){
        return ResponseEntity.ok(service.listarCitaById(citaId));
    }

    @GetMapping("/{citaId}/doctor")
    public DoctorDto listarDoctorPorCita(@PathVariable Long citaId){
        return service.getDoctorByCita(citaId);
    }
}
