package com.quispe.web.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaRequestDto {
    private LocalDate fecha;
    private String paciente;
    private Long doctorId;
    private String estado;
    private String notas;
    private LocalTime hora;
}
