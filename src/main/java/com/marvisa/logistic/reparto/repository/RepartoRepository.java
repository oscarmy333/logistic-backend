package com.marvisa.logistic.reparto.repository;


import com.marvisa.logistic.reparto.entity.Reparto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepartoRepository extends JpaRepository<Reparto, Long> {
}