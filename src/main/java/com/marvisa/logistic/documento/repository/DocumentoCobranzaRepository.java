package com.marvisa.logistic.documento.repository;


import com.marvisa.logistic.documento.entity.DocumentoCobranza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoCobranzaRepository  extends JpaRepository<DocumentoCobranza, Long> {
    List<DocumentoCobranza> findByClienteId(Long clienteId);
}