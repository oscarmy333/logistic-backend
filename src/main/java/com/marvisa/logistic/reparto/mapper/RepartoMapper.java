package com.marvisa.logistic.reparto.mapper;

import com.marvisa.logistic.reparto.dto.RepartoResponse;
import com.marvisa.logistic.reparto.entity.Reparto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RepartoMapper {

    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "clienteNombreCompleto", expression = "java(entity.getCliente().getNombres() + \" \" + entity.getCliente().getApellidos())")
    @Mapping(target = "envioId", source = "envio.id")
    RepartoResponse toResponse(Reparto entity);

    List<RepartoResponse> toResponseList(List<Reparto> entities);
}