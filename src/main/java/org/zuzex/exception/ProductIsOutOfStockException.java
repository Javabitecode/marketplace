package org.zuzex.exception;

public class ProductIsOutOfStockException extends ServiceException {
    public ProductIsOutOfStockException() {
    }

    public ProductIsOutOfStockException(String message) {
        super(message);
    }
}
