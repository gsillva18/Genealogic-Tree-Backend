package com.example.genealogictree.core.exceptions;

public class NotExistentAccountException extends Exception{

    public NotExistentAccountException(){
        super("Non-existing account");
    }
}
