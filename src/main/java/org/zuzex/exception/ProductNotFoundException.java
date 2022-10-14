package org.zuzex.exception;

public class ProductNotFoundException extends ServiceException{
    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
