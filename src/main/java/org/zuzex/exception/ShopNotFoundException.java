package org.zuzex.exception;

public class ShopNotFoundException extends ServiceException {

    public ShopNotFoundException() {
    }

    public ShopNotFoundException(String message) {
        super(message);
    }
}
