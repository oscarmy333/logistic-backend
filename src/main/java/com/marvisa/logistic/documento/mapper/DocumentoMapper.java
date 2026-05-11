package com.marvisa.logistic.documento.mapper;

import com.marvisa.logistic.cliente.entity.Cliente;
import com.marvisa.logistic.documento.dto.DocumentoResponse;
import com.marvisa.logistic.documento.entity.DocumentoCobranza;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentoMapper {
    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente", target = "clienteNombre", qualifiedByName = "formatClienteNombre")
    DocumentoResponse toResponse(DocumentoCobranza entity);
    List<DocumentoResponse> toResponseList(List<DocumentoCobranza> entities);

    @Named("formatClienteNombre")
    default String formatClienteNombre (Cliente cliente){
        if (cliente == null) return null;
        return String.format("%s %s",
                cliente.getNombres(),
                cliente.getApellidos()).trim();
    }
}
