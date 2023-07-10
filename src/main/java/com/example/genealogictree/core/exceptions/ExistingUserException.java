package com.example.genealogictree.core.exceptions;

public class ExistingUserException extends Exception{

    public ExistingUserException(){
        super("usuário já existente");
    }
}
