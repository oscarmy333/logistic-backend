package com.marvisa.logistic.envio.mapper;

import com.marvisa.logistic.envio.dto.EnvioResponse;
import com.marvisa.logistic.envio.entity.Envio;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnvioMapper {
    EnvioResponse toResponse(Envio entity);
    List<EnvioResponse> toResponseList(List<Envio> entities);
}