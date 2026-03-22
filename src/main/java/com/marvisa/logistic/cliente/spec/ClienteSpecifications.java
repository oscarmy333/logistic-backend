package com.marvisa.logistic.cliente.spec;

import com.marvisa.logistic.cliente.entity.Cliente;
import com.marvisa.logistic.common.spec.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;

public class ClienteSpecifications {
    private ClienteSpecifications() {
    }

    public static Specification<Cliente> filter(String nombres, String email, Boolean activo) {
        return Specification.where(SpecificationBuilder.<Cliente>isFalse("deleted"))
                .and(SpecificationBuilder.likeIgnoreCase("nombres", nombres))
                .and(SpecificationBuilder.likeIgnoreCase("email", email))
                .and(SpecificationBuilder.equal("activo", activo));
    }
}
