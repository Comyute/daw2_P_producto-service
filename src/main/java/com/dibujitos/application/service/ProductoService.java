package com.dibujitos.application.service;

import com.dibujitos.web.dto.ProductoRequestDto;
import com.dibujitos.web.dto.ProductoResponseDto;
import com.dibujitos.web.dto.CategoriaDto;

import java.util.List;

public interface ProductoService {
    ProductoResponseDto crearProducto(ProductoRequestDto request);
    ProductoResponseDto actualizarOferta(Long id, Boolean estadoOferta);
    List<ProductoResponseDto> listarProductos();
    ProductoResponseDto listarProductoById(Long id);
    CategoriaDto getCategoriaByProducto(Long citaId);
    List<ProductoResponseDto> listarProductosEnOferta();
}
