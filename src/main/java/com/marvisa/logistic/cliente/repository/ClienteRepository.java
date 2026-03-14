package com.marvisa.logistic.cliente.repository;

import com.marvisa.logistic.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}