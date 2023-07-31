package com.example.genealogictree.aspect;

import com.example.genealogictree.config.GenealogicTreeConfig;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class GenealogicTreeAspect {

    @Autowired
    private GenealogicTreeConfig genealogicTreeConfig;

    @Pointcut("within(com.example.genealogictree.api.*)")
    private void apiClass(){}

    @AfterReturning(pointcut = "apiClass()")
    public void afterRequestCompletion(){
        log.info(genealogicTreeConfig.getSendSplunk());//avisando ao append para enviar os logs para o splunk
    }
}
