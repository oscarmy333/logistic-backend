package com.marvisa.logistic.producto.repository;


import com.marvisa.logistic.producto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}