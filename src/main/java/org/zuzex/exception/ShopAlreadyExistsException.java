package org.zuzex.exception;


public class ShopAlreadyExistsException extends ServiceException {
    public ShopAlreadyExistsException() {
    }

    public ShopAlreadyExistsException(String message) {
        super(message);
    }
}
