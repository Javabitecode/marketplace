package org.zuzex.exception;

public class CategoryAlreadyExistsException extends ServiceException {
    public CategoryAlreadyExistsException() {
    }

    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}
