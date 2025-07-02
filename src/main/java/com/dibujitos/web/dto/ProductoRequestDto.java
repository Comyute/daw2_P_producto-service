package com.dibujitos.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoRequestDto {
    private String nombre;
    private String descripcion;
    private Double precio;
    private int stock;
    private Boolean oferta;
    private Long id_categoria;
}
