package com.hua.fileplat.cloud.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class OperatorImpl{
    public void before(JoinPoint joinPoint){
        System.out.println("operatorImpl before");
    }

    public void around(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("AOP Aronud before...");
        try {
            proceedingJoinPoint.proceed();//
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("AOP Aronud after...");
    }
}
