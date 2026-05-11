package com.marvisa.logistic.abono.repository;

import com.marvisa.logistic.abono.entity.Abono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AbonoRepository extends JpaRepository<Abono, Long> {
    List<Abono> findByDocumentoId(Long documentoId);

    @Query("select coalesce(sum(a.monto), 0) from Abono a where a.documento.id = :documentoId")
    Optional<BigDecimal> sumMontoByDocumentoId(Long documentoId);
}
