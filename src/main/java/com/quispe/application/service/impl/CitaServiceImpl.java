package com.quispe.application.service.impl;

import com.quispe.application.mapper.CitaMapper;
import com.quispe.application.service.AuthClient;
import com.quispe.application.service.CitaService;
import com.quispe.application.service.DoctorClient;
import com.quispe.domain.model.Cita;
import com.quispe.domain.model.EstadoCita;
import com.quispe.domain.repository.CitaRepository;
import com.quispe.web.dto.CitaRequestDto;
import com.quispe.web.dto.CitaResponseDto;
import com.quispe.web.dto.DoctorDto;
import com.quispe.web.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final CitaMapper citaMapper;

    private final AuthClient authClient;
    private final DoctorClient doctorClient;

    @Value("${auth.client-id}")
    private String clientId;
    @Value("${auth.client-secret}")
    private String clientSecret;

    @Override
    public CitaResponseDto crearCita(CitaRequestDto request) {
        Cita cita = citaMapper.toEntity(request);
        Cita saved = citaRepository.save(cita);
        log.info("Cita creada: {}", saved.getId());

        return citaMapper.toDto(saved);
    }

    @Override
    public CitaResponseDto actualizarEstado(Long id, String nuevoEstado) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setEstado(EstadoCita.valueOf(nuevoEstado));
        Cita updated = citaRepository.save(cita);

        return citaMapper.toDto(updated);
    }

    @Override
    public List<CitaResponseDto> listarCitas() {
        return citaRepository.findAll().stream()
                .map(citaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CitaResponseDto listarCitaById(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        return citaMapper.toDto(cita);
    }

    @Override
    public DoctorDto getDoctorByCita(Long citaId) {
        TokenResponse tokenResponse = authClient.getToken(Map.of("clientId", clientId, "clientSecret", clientSecret));
        String bearerToken = "Bearer " + tokenResponse.token();
        System.out.println("Token generado: " + tokenResponse.token());
        return doctorClient.getDoctorByCita(citaId
                                            , bearerToken
        );
    }
}
