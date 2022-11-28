package com.br.una.airport.service.exception;


import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException{ // É uma exception personalizada para dizer que o recurso não foi encontrado no banco de dados.

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
