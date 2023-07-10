package com.example.genealogictree.core.exceptions;

public class PasswordException extends Exception{

    public PasswordException(){
        super("Senha precis ter no mínimo 6 caracteres");
    }
}
