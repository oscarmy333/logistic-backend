package com.marvisa.logistic.vendedor.repository;


import com.marvisa.logistic.vendedor.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
}