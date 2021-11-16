package com.example.SearchableEncryption.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@EnableAspectJAutoProxy
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointCut() {

    }

    @Pointcut("within(com.example.SearchableEncryption.service..*)" +
            " || within(com.example.SearchableEncryption.controllers..*)" +
            " || within(com.example.SearchableEncryption.security..*)")
    public void applicationPackagePointCut() {

    }

    @Pointcut("within(com.example.SearchableEncryption.repository..*)")
    public void databasePointCut() {

    }

    @AfterThrowing(pointcut = "applicationPackagePointCut() && springBeanPointCut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.error("Exception in {} with cause = {}", joinPoint.getSignature().toShortString()
                , e.getCause() != null ? e.getCause() : "NULL");
    }

    @Around("databasePointCut()")
    public void logDatabaseAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (logger.isDebugEnabled()) {
            logger.debug("Database Access Call in {} with the following params {}."
                    , joinPoint.getSignature().toShortString()
                    , Arrays.toString(joinPoint.getArgs())
            );
        }

        Object result = joinPoint.proceed();
        if (logger.isDebugEnabled()) {
            logger.debug("Database Access End Call in {} with result: {}.", joinPoint.getSignature().toShortString(), result);
        }
    }

    @Around("applicationPackagePointCut() && springBeanPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter: {} with argument[s] = {}", joinPoint.getSignature().toShortString(),
                    Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (logger.isDebugEnabled()) {
                logger.debug("Exit: {} with result = {}", joinPoint.getSignature().toShortString(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            logger.error("Illegal argument: {} in {}", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().toShortString());
            throw e;
        }
    }
}
