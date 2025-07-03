package com.dibujitos.domain.repository;

import com.dibujitos.domain.model.Producto;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByOfertaTrue();

    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE CONCAT('%', :nombre, '%')")
    List<Producto> buscarPorNombre(@Param("nombre") String nombre);
}
