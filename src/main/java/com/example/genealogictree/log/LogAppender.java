package com.example.genealogictree.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class LogAppender extends AppenderBase<ILoggingEvent> {

    private final List<Log> listLogs = new ArrayList<>();

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {

        if (iLoggingEvent.getLevel() == Level.INFO && iLoggingEvent.getMessage().equals("SEND_SPLUNK")){
            ManagerLog.getInstance().finalizeLog(listLogs);
            listLogs.clear();
        }else{
            Log log = new Log(iLoggingEvent.getLevel().toString(),iLoggingEvent.getLoggerName(), iLoggingEvent.getFormattedMessage());
            listLogs.add(log);
        }
    }

}
