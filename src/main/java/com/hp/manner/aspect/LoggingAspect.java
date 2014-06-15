package com.hp.manner.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = Logger.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.hp.manner.controller.*.*(..))")
    private void selectAll(){}

    @Before("selectAll()")
    public void beforeAdvice(JoinPoint joinPoint){
        //logger.info("beforeAdvice() is running");
        logger.info("Start executing method: " + joinPoint.getSignature());
    }

    @After("selectAll()")
    public void afterAdvice(JoinPoint joinPoint){
        //logger.info("afterAdvice() is running");
        logger.info("End executing method: " + joinPoint.getSignature());
    }


}
