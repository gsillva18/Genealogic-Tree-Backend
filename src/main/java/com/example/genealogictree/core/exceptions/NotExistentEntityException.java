package com.example.genealogictree.core.exceptions;

public class NotExistentEntityException extends Exception{

    public NotExistentEntityException(){
        super("Non-existent entity");
    }
}
