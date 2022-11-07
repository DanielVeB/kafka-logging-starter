package com.kurosz.logging.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProcessedEventAspect {

    private final Logger logger = LoggerFactory.getLogger(ProcessedEventAspect.class);

    @Pointcut("@annotation(com.kurosz.logging.annotations.LogProcessedEvent)")
    public void logProcessedEvent() {
    }

    @Pointcut("target(org.apache.kafka.streams.processor.Processor)")
    public void kafkaProcessor(){}

    @Around("logProcessedEvent()")
    public Object processEvent(ProceedingJoinPoint joinPoint){
        logger.info("processed event");
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
