package com.marvisa.logistic.cliente.repository;

import com.marvisa.logistic.cliente.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<Cliente> findByDeletedFalseAndActivo(Boolean activo, Pageable pageable);

    Page<Cliente> findByDeletedFalseAndNombresContainingIgnoreCaseAndActivo(
            String nombres,
            Boolean activo,
            Pageable pageable
    );

    Page<Cliente> findByDeletedFalse(Pageable pageable);
}