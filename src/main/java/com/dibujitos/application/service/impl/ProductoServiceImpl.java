package com.dibujitos.application.service.impl;

import com.dibujitos.application.mapper.ProductoMapper;
import com.dibujitos.application.service.AuthClient;
import com.dibujitos.application.service.ProductoService;
import com.dibujitos.application.service.CategoriaClient;
import com.dibujitos.domain.model.Producto;
import com.dibujitos.domain.repository.ProductoRepository;
import com.dibujitos.web.dto.ProductoRequestDto;
import com.dibujitos.web.dto.ProductoResponseDto;
import com.dibujitos.web.dto.CategoriaDto;
import com.dibujitos.web.dto.TokenResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    private final AuthClient authClient;
    private final CategoriaClient categoriaClient;

    @Value("${auth.client-id}")
    private String clientId;
    @Value("${auth.client-secret}")
    private String clientSecret;

    @Override
    public ProductoResponseDto crearProducto(ProductoRequestDto request) {
        Producto producto = productoMapper.toEntity(request);
        Producto saved = productoRepository.save(producto);
        log.info("Producto creada: {}", saved.getId());

        return productoMapper.toDto(saved);
    }

    @Override
    public ProductoResponseDto actualizarOferta(Long id, Boolean estadoOferta) {
        System.out.println("id: " + id + " estado: " + estadoOferta);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrada"));
        producto.setOferta(estadoOferta);
        Producto updated = productoRepository.save(producto);
        return productoMapper.toDto(updated);
    }

    @Override
    public List<ProductoResponseDto> listarProductos() {
        return productoRepository.findAll().stream()
                .map(productoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoResponseDto listarProductoById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return productoMapper.toDto(producto);
    }

    @Override
    @CircuitBreaker(name = "categoriaService", fallbackMethod = "fallbackCategoria")
    public CategoriaDto getCategoriaByProducto(Long productoId) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Long categoriaId = producto.getId_categoria();

        TokenResponse tokenResponse = authClient.getToken(Map.of("clientId", clientId, "clientSecret", clientSecret));
        String bearerToken = "Bearer " + tokenResponse.token();
        System.out.println("Token generado: " + tokenResponse.token());

        return categoriaClient.getCategoriaById(categoriaId
                                            , bearerToken
        );
    }

    @Override
    public List<ProductoResponseDto> listarProductosEnOferta() {
        return productoRepository.findByOfertaTrue().stream()
                .map(productoMapper::toDto)
                .collect(Collectors.toList());
    }

    public CategoriaDto fallbackCategoria(Long id, Throwable throwable){
        log.warn("Fallback ejecutado: {}", throwable.getMessage());
        return new CategoriaDto(0L,"Categoria no disponible");
    }

}
