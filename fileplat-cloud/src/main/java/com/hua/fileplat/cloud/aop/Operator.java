package com.hua.fileplat.cloud.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/*@Component
@Aspect //切面*/
public class Operator {



    /**
     * @Pointcut 切点描述
     */
    @Pointcut("execution(* com.hua.fileplat.cloud.aop.service.UserService.*())")
    public void pointCut(){
        System.out.println("-----------------------------------------Pointcut");
    }
    /**
     * 切面被调用之前
     * @param joinPoint
     */
    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        System.out.println("-----------------------------------------before");
    }
    /**
     * 无论 一个 join point 是正常退出 还是 发生异常，都会被执行的 advice
     * @param joinPoint
     */
    @After(value = "pointCut()")
    public void after(JoinPoint joinPoint){
        joinPoint.getArgs();
        System.out.println("after");
    }

    /**
     * 切面被调用时抛出错误  时  调用
     */
    @AfterThrowing(pointcut = "pointCut()",throwing = "error")
    public void afterThrowing(JoinPoint joinPoint,Throwable error){
        System.out.println("afterThrowing");
    }


    /**
     * 正常返回时调用
     * @param joinPoint
     * @param returnVal
     */
    @AfterReturning(pointcut = "pointCut()",returning = "returnVal")
    public void afterReturn(JoinPoint joinPoint,Object returnVal){
        System.out.println("afterReturn");
    }

    /**
     * before,after 都被执行后 调用
     */
    @Around("pointCut()")
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
