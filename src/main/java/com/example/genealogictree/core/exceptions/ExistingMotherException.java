package com.example.genealogictree.core.exceptions;

public class ExistingMotherException extends Exception{

    public ExistingMotherException(){
        super("existing mother for this person");
    }
}
