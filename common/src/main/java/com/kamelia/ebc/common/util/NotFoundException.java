package com.kamelia.ebc.common.util;

public final class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super("Not found");
    }

}
