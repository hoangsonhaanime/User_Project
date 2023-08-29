package com.example.practise_crud.service;

public class NotFoundException extends Throwable{
    public NotFoundException(String message) {
        super(message);
    }
}
