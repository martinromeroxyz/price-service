package com.inditex.price.modules.infrastructure.error;

public class PriceException extends RuntimeException {
    public PriceException(String s) {
        super( s );
    }
}
