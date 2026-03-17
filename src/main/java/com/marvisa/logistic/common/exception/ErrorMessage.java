package com.marvisa.logistic.common.exception;

public enum ErrorMessage {

    VENDEDOR_NOT_FOUND("Vendedor not found with the ID: %d"),
    REMOVE_VENDEDOR_ID_FROM_REQUEST("Do not include Vendedor ID on create"),
    VENDEDOR_ID_MUST_BE_PROVIDED("Vendedor ID must be provided for update"),
    VENDEDOR_EMAIL_ALREADY_EXISTS("Vendedor with email %s already exists"),

    CLIENTE_NOT_FOUND("Cliente not found with the ID: %d"),
    REMOVE_CLIENTE_ID_FROM_REQUEST("Do not include Cliente ID on create"),
    CLIENTE_ID_MUST_BE_PROVIDED("Cliente ID must be provided for update"),
    CLIENTE_EMAIL_ALREADY_EXISTS("Cliente with email %s already exists");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String formatMessage(Object... args) {
        return String.format(this.message, args);
    }
}