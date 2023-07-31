package com.example.genealogictree.log;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Log {

    private String level;
    private String logger;
    private String message;

    public Log(String level, String logger, String message){
        this.level = level;
        this.logger = logger;
        this.message = message;
    }
}
