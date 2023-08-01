package com.example.genealogictree.core.exceptions;

public class ExistingPersonInTreeException extends Exception{

    public ExistingPersonInTreeException(){
        super("existing person in genealogical tree");
    }
}
