package com.example.webclient.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class myAspect {

    @Around("@annotation(com.example.webclient.aspect.LogMethod)")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Вызван метод: " + joinPoint.getSignature().getName()
                + Arrays.toString(joinPoint.getArgs()));
        Object proceed = joinPoint.proceed();
        return proceed;
    }

}
