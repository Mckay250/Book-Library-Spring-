package com.example.demo.Exception;

public class MyFileNotFoundException extends  RuntimeException {
    private static final Long serialVersionUID = 1L;

    public MyFileNotFoundException(String message) {
        super(message);
    }

    public  MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
