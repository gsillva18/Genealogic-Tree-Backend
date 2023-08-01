package com.example.genealogictree.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Getter
@Configuration
@EnableAspectJAutoProxy
public class GenealogicTreeConfig {

    @Value("${genealogictree.config.initial-layer}")
    private Integer initialLayer;

    @Value("${genealogictree.logs.send-splunk}")
    private String sendSplunk;

}
