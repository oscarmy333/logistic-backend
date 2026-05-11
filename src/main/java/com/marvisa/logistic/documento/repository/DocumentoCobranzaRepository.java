package com.marvisa.logistic.documento.repository;


import com.marvisa.logistic.documento.entity.DocumentoCobranza;
import com.marvisa.logistic.documento.enums.EstadoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoCobranzaRepository  extends JpaRepository<DocumentoCobranza, Long> {
    List<DocumentoCobranza> findByClienteId(Long clienteId);
    List<DocumentoCobranza> findByEstado(EstadoDocumento estado);
}