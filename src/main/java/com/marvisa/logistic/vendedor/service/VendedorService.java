package com.marvisa.logistic.vendedor.service;


import com.marvisa.logistic.common.exception.ResourceNotFoundException;
import com.marvisa.logistic.common.pagination.PageResponse;
import com.marvisa.logistic.vendedor.dto.VendedorRequest;
import com.marvisa.logistic.vendedor.dto.VendedorResponse;
import com.marvisa.logistic.vendedor.entity.Vendedor;
import com.marvisa.logistic.vendedor.mapper.VendedorMapper;
import com.marvisa.logistic.vendedor.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VendedorService {

    private final boolean VENDEDOR_ACTIVO = true;
    private final VendedorRepository vendedorRepository;
    private final VendedorMapper vendedorMapper;

    public PageResponse<VendedorResponse> listar(String nombres, Boolean activo, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Vendedor> result;

        if (nombres != null && !nombres.isBlank() && activo != null) {
            result = vendedorRepository.findByDeletedFalseAndNombresContainingIgnoreCaseAndActivo(nombres, activo, pageable);
        } else if (nombres != null && !nombres.isBlank()) {
            result = vendedorRepository.findByDeletedFalseAndNombresContainingIgnoreCase(nombres, pageable);
        } else if (activo != null) {
            result = vendedorRepository.findByDeletedFalseAndActivo(activo, pageable);
        } else {
            result = vendedorRepository.findByDeletedFalse(pageable);
        }

        return new PageResponse<>(
                vendedorMapper.toResponseList(result.getContent()),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.isLast()
        );
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
                .activo(request.getActivo() != null ? request.getActivo() : VENDEDOR_ACTIVO)
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