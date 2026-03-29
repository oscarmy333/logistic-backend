package com.marvisa.logistic.cobranza.repository;

import com.marvisa.logistic.cobranza.entity.DocumentoCobranza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<DocumentoCobranza, Long> {
}
