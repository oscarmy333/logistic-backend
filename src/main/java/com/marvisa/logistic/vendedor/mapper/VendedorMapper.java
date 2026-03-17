package com.marvisa.logistic.vendedor.mapper;

import com.marvisa.logistic.vendedor.dto.VendedorResponse;
import com.marvisa.logistic.vendedor.entity.Vendedor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendedorMapper {
    VendedorResponse toResponse(Vendedor entity);
    List<VendedorResponse> toResponseList(List<Vendedor> entities);
}