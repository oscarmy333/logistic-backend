package com.marvisa.logistic.abono.repository;

import com.marvisa.logistic.abono.entity.Abono;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbonoRepository extends JpaRepository<Abono, Long> {
    List<Abono> findByDocumentoId(Long documentoId);
}
