package com.marvisa.logistic.documento.mapper;

import com.marvisa.logistic.documento.dto.DocumentoResponse;
import com.marvisa.logistic.documento.entity.DocumentoCobranza;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentoMapper {
    DocumentoResponse toResponse(DocumentoCobranza entity);
    List<DocumentoResponse> toResponseList(List<DocumentoCobranza> entities);
}
