package com.marvisa.logistic.producto.service;

import com.marvisa.logistic.common.exception.ResourceNotFoundException;
import com.marvisa.logistic.producto.dto.ProductoRequest;
import com.marvisa.logistic.producto.entity.Producto;
import com.marvisa.logistic.producto.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    public Producto obtener(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
    }

    public Producto crear(ProductoRequest request) {
        Producto producto = Producto.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .activo(request.getActivo() != null ? request.getActivo() : true)
                .build();

        return productoRepository.save(producto);
    }

    public Producto actualizar(Long id, ProductoRequest request) {
        Producto producto = obtener(id);
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setActivo(request.getActivo() != null ? request.getActivo() : producto.getActivo());

        return productoRepository.save(producto);
    }

    public void eliminar(Long id) {
        Producto producto = obtener(id);
        productoRepository.delete(producto);
    }
}