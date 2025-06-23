package com.quispe.application.service;

import com.quispe.web.dto.CitaRequestDto;
import com.quispe.web.dto.CitaResponseDto;
import com.quispe.web.dto.DoctorDto;

import java.util.List;

public interface CitaService {
    CitaResponseDto crearCita(CitaRequestDto request);
    CitaResponseDto actualizarEstado(Long id, String nuevoEstado);
    List<CitaResponseDto> listarCitas();
    CitaResponseDto listarCitaById(Long id);
    DoctorDto getDoctorByCita(Long citaId);
}
