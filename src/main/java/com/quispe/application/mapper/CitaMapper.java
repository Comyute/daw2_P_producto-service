package com.quispe.application.mapper;

import com.quispe.domain.model.Cita;
import com.quispe.domain.model.EstadoCita;
import com.quispe.web.dto.CitaRequestDto;
import com.quispe.web.dto.CitaResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CitaMapper {

    public Cita toEntity(CitaRequestDto dto){
        return Cita.builder()
                .fecha(dto.getFecha())
                .paciente(dto.getPaciente())
                .doctorId(dto.getDoctorId())
                .estado(EstadoCita.valueOf(dto.getEstado()))
                .notas(dto.getNotas())
                .hora(dto.getHora())
                .build();
    }

    public CitaResponseDto toDto(Cita entity){
        return CitaResponseDto.builder()
                .id(entity.getId())
                .fecha(entity.getFecha())
                .paciente(entity.getPaciente())
                .doctorId(entity.getDoctorId())
                .estado(entity.getEstado().name())
                .notas(entity.getNotas())
                .hora(entity.getHora())
                .build();
    }
}
