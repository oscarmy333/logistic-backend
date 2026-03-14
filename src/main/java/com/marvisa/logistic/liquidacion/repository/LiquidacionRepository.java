package com.marvisa.logistic.liquidacion.repository;

import com.marvisa.logistic.liquidacion.entity.Liquidacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiquidacionRepository extends JpaRepository<Liquidacion, Long> {
}