package com.example.demo_test.exception.custom;

public class ApiNotFoundException extends Exception{
    public ApiNotFoundException() {
        super("NOT_FOUND");
    }
}
