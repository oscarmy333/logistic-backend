package com.marvisa.logistic.cliente.service;

import com.marvisa.logistic.cliente.dto.ClienteRequest;
import com.marvisa.logistic.cliente.dto.ClienteResponse;
import com.marvisa.logistic.cliente.entity.Cliente;
import com.marvisa.logistic.cliente.mapper.ClienteMapper;
import com.marvisa.logistic.cliente.repository.ClienteRepository;
import com.marvisa.logistic.common.exception.ResourceNotFoundException;
import com.marvisa.logistic.common.pagination.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final boolean CLIENTE_ACTIVO = true;

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public PageResponse<ClienteResponse> listar(String nombres, Boolean activo, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Cliente> result;

        if (nombres != null && !nombres.isBlank() && activo != null) {
            result = clienteRepository.findByDeletedFalseAndNombresContainingIgnoreCaseAndActivo(nombres, activo, pageable);
        } else if (activo != null) {
            result = clienteRepository.findByDeletedFalseAndActivo(activo, pageable);
        } else {
            result = clienteRepository.findByDeletedFalse(pageable);
        }

        return new PageResponse<>(
                clienteMapper.toResponseList(result.getContent()),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.isLast()
        );
    }

    public ClienteResponse obtener(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
        return clienteMapper.toResponse(cliente);
    }

    public ClienteResponse crear(ClienteRequest request) {
        Cliente cliente = Cliente.builder()
                .codigo(request.getCodigo())
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .correo(request.getEmail())
                .telefono(request.getTelefono())
                .direccion(request.getDireccion())
                .activo(request.getActivo() != null ? request.getActivo() : CLIENTE_ACTIVO)
                .build();

        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    public ClienteResponse actualizar(Long id, ClienteRequest request) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado - sin actualizar: " + id));

        cliente.setNombres(request.getNombres());
        cliente.setApellidos(request.getApellidos());
        cliente.setCorreo(request.getEmail());
        cliente.setTelefono(request.getTelefono());
        cliente.setDireccion(request.getDireccion());
        cliente.setActivo(request.getActivo() != null ? request.getActivo() : cliente.getActivo());

        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    public void eliminar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado - Sin eliminar: " + id));
        clienteRepository.delete(cliente);
    }
}