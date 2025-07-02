package com.dibujitos.web.controller;

import com.dibujitos.application.service.ProductoService;
import com.dibujitos.web.dto.ProductoRequestDto;
import com.dibujitos.web.dto.ProductoResponseDto;
import com.dibujitos.web.dto.CategoriaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService service;

    @GetMapping
    public ResponseEntity<List<ProductoResponseDto>> listar() {
        return ResponseEntity.ok(service.listarProductos());
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDto> crear(@RequestBody ProductoRequestDto request){
        return ResponseEntity.ok(service.crearProducto(request));
    }

    @GetMapping("/{productoId}")
    public ResponseEntity<ProductoResponseDto> listarPorId(@PathVariable Long productoId){
        return ResponseEntity.ok(service.listarProductoById(productoId));
    }

    @PutMapping("{id}/estado")
    public ResponseEntity<ProductoResponseDto> actualizarOferta(
            @PathVariable Long id,
            @RequestParam Boolean estadoOferta
    ){
        return ResponseEntity.ok(service.actualizarOferta(id, estadoOferta));
    }

    @GetMapping("/listar-oferta")
    public ResponseEntity<List<ProductoResponseDto>> listarPorOfertas(){
        return ResponseEntity.ok(service.listarProductosEnOferta());
    }

    @GetMapping("/{productoId}/categoria")
    public CategoriaDto listarProductoPorCategoria(@PathVariable Long productoId){
        return service.getCategoriaByProducto(productoId);
    }
}
