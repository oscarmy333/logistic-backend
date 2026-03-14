package com.marvisa.logistic.envio.repository;

import com.marvisa.logistic.envio.entity.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvioRepository extends JpaRepository<Envio, Long> {
}