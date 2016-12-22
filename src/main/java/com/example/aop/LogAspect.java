package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kimyongyeon on 2016-12-22.
 */
@Aspect
@Component
public class LogAspect {

    // 로그인 처리 확인 을 위해
    @Pointcut("execution(public * com..*Service.*(..))")
    private void profileTarget(){};

    // main 클래스에서 service를 호출하는 부분에 로그를 확인하기 위해
    @Pointcut("execution(public * com..*service.*.Main*(..))")
    private void checkTarget(){};


    @Around("profileTarget()")
    public Object allLog(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("로그인 화면 시작");

        try{
            Object ret = joinPoint.proceed();
            return ret;
        }finally{
            System.out.println("로그인 화면 끝");
        }
    }

    @Around("checkTarget()")
    public Object allCheck(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("** check start **");

        try{
            Object ret = joinPoint.proceed();
            return ret;
        }finally{
            System.out.println("** check end **");
        }
    }
}
