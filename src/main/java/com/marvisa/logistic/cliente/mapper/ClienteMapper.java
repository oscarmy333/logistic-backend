package com.marvisa.logistic.cliente.mapper;

import com.marvisa.logistic.cliente.dto.ClienteResponse;
import com.marvisa.logistic.cliente.entity.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteResponse toResponse(Cliente entity);
    List<ClienteResponse> toResponseList(List<Cliente> entities);
}