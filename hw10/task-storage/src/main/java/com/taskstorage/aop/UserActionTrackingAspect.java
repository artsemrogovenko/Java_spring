package com.taskstorage.aop;

import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@AllArgsConstructor
public class UserActionTrackingAspect {
    private static final Logger logger = LoggerFactory.getLogger(UserActionTrackingAspect.class);


    // перед каждым вызовом функции будет работать этот метод
    @Before("@annotation(com.aop.taskstorage.TrackUserAction)")
    public void trackUserAction(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().toString();
        Object[] args = joinPoint.getArgs();
        logger.info("Пользователь  вызвал  " + methodName + " с аргументами: " + Arrays.toString(args));
    }
}