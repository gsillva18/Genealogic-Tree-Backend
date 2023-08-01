package com.example.genealogictree.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Log {

    private String level;
    private String logger;
    private String message;
    private StackTraceElement[] stackTraceElements;

    public Log(ILoggingEvent iLoggingEvent){
        this.level = iLoggingEvent.getLevel().toString();
        this.logger = iLoggingEvent.getLoggerName();
        this.message = iLoggingEvent.getFormattedMessage();
        this.stackTraceElements = iLoggingEvent.getCallerData();
    }
}
