package com.josue.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by Josue on 11/07/2016.
 */
@Aspect
@Component
public class LoggingInterceptor {

    private static final Logger logger = Logger.getLogger(LoggingInterceptor.class.getName());

//    @Pointcut("@annotation(com.josue.interceptor.Logged)")
//    public void annotationPointcut() {
//    }


    @Around("@annotation(com.josue.interceptor.Logged)")
    public Object log(ProceedingJoinPoint call) throws Throwable {

        MethodSignature signature = (MethodSignature) call.getSignature();

        String value = signature.getMethod().getAnnotation(Logged.class).value();
        logger.info(">>> " + value);

        logger.info(":: BEFORE INTERCEPTING METHOD '" + signature.getDeclaringType().getName() + "#" + signature.getName() + "' ::");
        Object proceed = call.proceed();
        logger.info(":: AFTER INTERCEPTING METHOD '" + signature.getDeclaringType().getName() + "#" + signature.getName() + "' ::");
        return proceed + " >>> " + value;
    }
}