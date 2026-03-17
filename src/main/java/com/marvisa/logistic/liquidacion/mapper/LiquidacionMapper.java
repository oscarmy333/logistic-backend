package com.marvisa.logistic.liquidacion.mapper;

import com.marvisa.logistic.liquidacion.dto.LiquidacionResponse;
import com.marvisa.logistic.liquidacion.entity.Liquidacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LiquidacionMapper {

    @Mapping(target = "vendedorId", source = "vendedor.id")
    @Mapping(target = "vendedorNombreCompleto", expression = "java(entity.getVendedor().getNombres())")
    LiquidacionResponse toResponse(Liquidacion entity);

    List<LiquidacionResponse> toResponseList(List<Liquidacion> entities);
}