package com.qrowdsy.domain.exception;

public class DomainException extends Exception{

    private final String detail;
    
    public DomainException(String detail) {
        this.detail = detail;
    }

    public String detail() {
        return detail;
    }
}
