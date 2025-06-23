package com.quispe.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "citas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String paciente;
    private Long doctorId;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    private String notas;
    private LocalTime hora;
}
