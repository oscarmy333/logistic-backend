package com.marvisa.logistic.abono.mapper;

import com.marvisa.logistic.abono.dto.AbonoResponse;
import com.marvisa.logistic.abono.entity.Abono;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AbonoMapper {
    AbonoResponse toResponse(Abono entity);
    List<AbonoResponse> toResponseList(List<Abono> entities);
}