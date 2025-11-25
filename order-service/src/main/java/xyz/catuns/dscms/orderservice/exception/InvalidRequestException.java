package xyz.catuns.dscms.orderservice.exception;

public class InvalidRequestException extends RuntimeException {

    private final Object cause;

    public InvalidRequestException(Object cause) {
        super();
        this.cause = cause;
    }

    public String getCauseName() {
        return this.cause.getClass().getName();
    }
}
