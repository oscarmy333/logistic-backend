package com.marvisa.logistic.vendedor.service;


import com.marvisa.logistic.common.exception.ResourceNotFoundException;
import com.marvisa.logistic.vendedor.dto.VendedorRequest;
import com.marvisa.logistic.vendedor.entity.Vendedor;
import com.marvisa.logistic.vendedor.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendedorService {

    private final VendedorRepository vendedorRepository;

    public List<Vendedor> listar() {
        return vendedorRepository.findAll();
    }

    public Vendedor obtener(Long id) {
        return vendedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor no encontrado con id: " + id));
    }

    public Vendedor crear(VendedorRequest request) {
        Vendedor vendedor = Vendedor.builder()
                .codigo(request.getCodigo())
                .dni(request.getDni())
                .nombres(request.getNombres())
                .licencia(request.getLicencia())
                .celular(request.getCelular())
                .email(request.getEmail())
                .direccion(request.getDireccion())
                .usuario(request.getUsuario())
                .activo(request.getActivo() != null ? request.getActivo() : true)
                .build();

        return vendedorRepository.save(vendedor);
    }

    public Vendedor actualizar(Long id, VendedorRequest request) {
        Vendedor vendedor = obtener(id);
        vendedor.setDni(request.getDni());
        vendedor.setNombres(request.getNombres());
        vendedor.setLicencia(request.getLicencia());
        vendedor.setCelular(request.getCelular());
        vendedor.setEmail(request.getEmail());
        vendedor.setDireccion(request.getDireccion());
        vendedor.setUsuario(request.getUsuario());
        vendedor.setActivo(request.getActivo() != null ? request.getActivo() : vendedor.getActivo());
        return vendedorRepository.save(vendedor);
    }

    public void eliminar(Long id) {
        Vendedor vendedor = obtener(id);
        vendedorRepository.delete(vendedor);
    }
}