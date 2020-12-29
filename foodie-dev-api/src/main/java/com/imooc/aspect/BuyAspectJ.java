package com.imooc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class BuyAspectJ {
    @Before("execution(* com.imooc.service.impl..*.*(..))")
    public void hehe() {
        System.out.println("before ...");
    }

    @After("execution(* com.imooc.service.impl..*.*(..))")
    public void haha() {
        System.out.println("After ...");
    }

    @AfterReturning("execution(* com.imooc.service.impl..*.*(..))")
    public void xixi() {
        System.out.println("AfterReturning ...");
    }

    @Around("execution(* com.imooc.service.impl..*.*(..))")
    public void xxx(ProceedingJoinPoint pj) {
        try {
            System.out.println("Around aaa ...");
            pj.proceed();
            System.out.println("Around bbb ...");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}