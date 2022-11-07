package com.kurosz.kafkalogginexample.aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogExecutionTimeRestControllerAspect {

    private final Logger logger = LoggerFactory.getLogger(LogExecutionTimeRestControllerAspect.class);

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restController() {
    }

    @Around("restController()")
    public Object logExecuteControllerMethod(ProceedingJoinPoint joinPoint){
        logger.info("rest endpoint executed");
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
