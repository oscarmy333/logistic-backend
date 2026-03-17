package com.marvisa.logistic.producto.mapper;

import com.marvisa.logistic.producto.dto.ProductoResponse;
import com.marvisa.logistic.producto.entity.Producto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    ProductoResponse toResponse(Producto entity);
    List<ProductoResponse> toResponseList(List<Producto> entities);
}