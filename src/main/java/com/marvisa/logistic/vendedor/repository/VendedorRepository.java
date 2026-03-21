package com.marvisa.logistic.vendedor.repository;


import com.marvisa.logistic.vendedor.entity.Vendedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    Page<Vendedor> findByDeletedFalse(Pageable pageable);

    Page<Vendedor> findByDeletedFalseAndActivo(Boolean activo, Pageable pageable);

    Page<Vendedor> findByDeletedFalseAndNombresContainingIgnoreCase(String nombres, Pageable pageable);

    Page<Vendedor> findByDeletedFalseAndNombresContainingIgnoreCaseAndActivo(
            String nombres,
            Boolean activo,
            Pageable pageable
    );
}