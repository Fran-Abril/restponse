package io.github.franabril.restponse.response.utils;

import io.github.franabril.baseexception.IError;

public enum ExampleEnumError implements IError {
    GENERIC("The project was failed because {%s}"),
    CLIENT_ERROR("The client {%s} does not have access rights to the content"),
    SERVER_ERROR("The request method {%s} is not supported by the server and cannot be handled");

    private final String reason;

    ExampleEnumError(final String reason) {
        this.reason = reason;
    }

    @Override
    public String getCode() {
        return this.getClass().getSimpleName() + "." + this.name();
    }

    @Override
    public String getReason() {
        return reason;
    }
}