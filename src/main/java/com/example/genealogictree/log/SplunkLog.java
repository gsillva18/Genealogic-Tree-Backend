package com.example.genealogictree.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SplunkLog {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void sendToSplunk(LogGenealogicTree logGenealogicTree){

        try {
            String object = objectMapper.writeValueAsString(logGenealogicTree);
            log.trace(object);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}
