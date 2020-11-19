package com.apsoft.conta.finance.exception;

public class NumberException extends Exception {
    private int number;


    public NumberException(int number){
        super(String.format("The numbes is exist : '%s'", number));
    }
}
