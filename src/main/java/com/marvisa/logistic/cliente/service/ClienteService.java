package com.marvisa.logistic.cliente.service;

import com.marvisa.logistic.cliente.dto.ClienteRequest;
import com.marvisa.logistic.cliente.entity.Cliente;
import com.marvisa.logistic.cliente.repository.ClienteRepository;
import com.marvisa.logistic.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente obtener(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
    }

    public Cliente crear(ClienteRequest request) {
        Cliente cliente = Cliente.builder()
                .codigo(request.getCodigo())
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .telefono(request.getTelefono())
                .direccion(request.getDireccion())
                .activo(request.getActivo() != null ? request.getActivo() : true)
                .build();

        return clienteRepository.save(cliente);
    }

    public Cliente actualizar(Long id, ClienteRequest request) {
        Cliente cliente = obtener(id);
        cliente.setNombres(request.getNombres());
        cliente.setApellidos(request.getApellidos());
        cliente.setCorreo(request.getEmail());
        cliente.setTelefono(request.getTelefono());
        cliente.setDireccion(request.getDireccion());
        cliente.setActivo(request.getActivo() != null ? request.getActivo() : cliente.getActivo());

        return clienteRepository.save(cliente);
    }

    public void eliminar(Long id) {
        Cliente cliente = obtener(id);
        clienteRepository.delete(cliente);
    }
}