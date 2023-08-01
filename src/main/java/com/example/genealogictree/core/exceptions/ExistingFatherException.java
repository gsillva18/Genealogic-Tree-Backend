package com.example.genealogictree.core.exceptions;

public class ExistingFatherException extends Exception{

    public ExistingFatherException(){
        super("existing father biologic for this person");
    }
}
