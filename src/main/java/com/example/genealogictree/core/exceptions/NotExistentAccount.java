package com.example.genealogictree.core.exceptions;

public class NotExistentAccount extends Exception{

    public NotExistentAccount(){
        super("Non-existing account");
    }
}
