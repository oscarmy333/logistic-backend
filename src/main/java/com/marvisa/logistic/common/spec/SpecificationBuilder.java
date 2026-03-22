package com.marvisa.logistic.common.spec;

import org.springframework.data.jpa.domain.Specification;

public final class SpecificationBuilder {
    private SpecificationBuilder() {
    }

    public static <T> Specification<T> alwaysTrue() {
        return (root, query, cb) -> cb.conjunction();
    }

    public static <T> Specification<T> equal(String field, Object value) {
        return (root, query, cb) ->
                value == null ? cb.conjunction() : cb.equal(root.get(field), value);
    }

    public static <T> Specification<T> likeIgnoreCase(String field, String value) {
        return (root, query, cb) ->
                (value == null || value.isBlank())
                        ? cb.conjunction()
                        : cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%");
    }

    public static <T> Specification<T> isFalse(String field) {
        return (root, query, cb) -> cb.isFalse(root.get(field));
    }
}
