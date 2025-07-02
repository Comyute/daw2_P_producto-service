package com.dibujitos.application.mapper;

import com.dibujitos.domain.model.Producto;
import com.dibujitos.web.dto.ProductoRequestDto;
import com.dibujitos.web.dto.ProductoResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public Producto toEntity(ProductoRequestDto dto){
        return Producto.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .oferta(dto.getOferta())
                .id_categoria(dto.getId_categoria())
                .build();
    }

    public ProductoResponseDto toDto(Producto entity){
        return ProductoResponseDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .precio(entity.getPrecio())
                .stock(entity.getStock())
                .oferta(entity.getOferta())
                .id_categoria(entity.getId_categoria())
                .build();
    }
}
