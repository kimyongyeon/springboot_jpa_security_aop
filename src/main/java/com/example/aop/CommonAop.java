package com.example.aop;

import com.example.vo.UserVO;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Created by kimyongyeon on 2016-12-21.
 * 어드바이스(Advice)라 불리는 메써드를 작성할 수 있다. 대상 스프링 빈의 메써드의 호출에 끼어드는 시점과 방법에 따라 @Before, @After, @AfterReturning, @AfterThrowing, @Around 등을 명시할 수 있다.
 */
@Aspect
@Component // component 등록하지 않으면 aop가 등록되질 않는다.
@Order(Ordered.LOWEST_PRECEDENCE) //  @Aspect 빈 간의 작동 순서를 정할 수 있다, 낮을 수록 우선 순위가 높다.
public class CommonAop {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @param joinPoint
     * @Aspect 클래스의 메써드 레벨에 @Before 어드바이스를 명시하면 대상 메써드의 실행 전에 끼어 들어 원하는 작업을 할 수 있다.
     * 끼어들기만 할 뿐 대상 메써드의 제어나 가공은 불가능하다.
     * 어드바이스에 작성된 파라메터는 PointCut이라 부르는 표현식이다. 끼어들 메써드의 범위를 지정할 수 있다.
     * 아규먼트로 전달 받는 JoinPoint 오브젝트는 끼어든 메써드의 정보를 담고 있다.
     *
     * org.aspectj.lang.JoinPoint 기능
     * 1. Object[] getArgs() : 전달되는 모든 파라미터를 Object의 배열로 가져온다.
     * 2. String getKind() : 해당 Advice의 타입을 알아낸다.
     * 3. Signature getSignature() : 실행하는 대상 객체의 메소드에 대한 정보를 알아낼때 사용
     * 4. Object getTarget() : target 객체를 알아낼 때 사용
     * 5. Object getThis() : Advice를 행하는 객체를 알아낼때 사용
     */
    @Before("execution(* com..*Controller.*(..))")
    public void loggingAdvice1(JoinPoint joinPoint) {
        logger.error("============ > logger start ");
        System.out.println(joinPoint.getSignature()); // 전달되는 메소드
        System.out.println(Arrays.toString(joinPoint.getArgs())); // 전달되는 모든 파라미터

        /**
         * 코드 변경
         * 참고사이트 : http://stackoverflow.com/questions/27659523/retrieve-parameter-value-from-proceedingjoinpoint
         * method parameter 가져오기 부분 추가
         */
        Object params[] = joinPoint.getArgs();
        for(Object param : params) {
            if(param instanceof UserVO) {
                System.out.println(ToStringBuilder.reflectionToString((UserVO)param));
            }
        }


        /**
         AOP Logging Result
         2016-02-22:13:28:06.361 INFO  7727 --- [main] com.aspect.AdviceLogging.loggingAdvice(AdviceLogging.java:31) : method path : User com.user.controller.UserRestController.createUser(UserVo)
         2016-02-22:13:28:06.371 INFO  7737 --- [main] com.aspect.AdviceLogging.loggingAdvice(AdviceLogging.java:36) : com.entity.UserVo@42ac84a9[name=홍길동,email=test@test.com,gender=male]
         */
    }

    /**
     * @param joinPoint
     * @Around 어드바이스는 앞서 설명한 어드바이스의 기능을 모두 포괄하는 종합선물세트와도 같다.
     * 대상 메써드를 감싸는 느낌으로 실행 전후 시점에 원하는 작업을 할 수 있다. 대상 메써드의 실행 제어 및 리턴 값 가공도 가능하다.
     * Object proceed() : 다음 Advice를 실행하거나, 실제 target 객체의 메소드를 실행하는 기능
     */
    @Around("execution(* com..*Controller.*(..))")
    public Object allLog(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("allLog start");

        try{
            logger.error(Arrays.toString(joinPoint.getArgs()));
            Object ret = joinPoint.proceed();
            logger.error(joinPoint.getSignature().getName());
            return ret;
        }finally{
            System.out.println("allLog end");
        }
    }

    /**
     * @param joinPoint
     * @After 어드바이스를 명시하면 대상 메써드의 실행 후에 끼어 들어 원하는 작업을 할 수 있다.
     * 역시 끼어들기만 할 뿐 대상 메써드의 제어나 가공은 불가능하다.
     */
    @After("execution(* com..*Controller.*(..))")
    public void loggingAdvice3(JoinPoint joinPoint) {
        System.out.println("After start");
    }

    /**
     * @param joinPoint
     * @param result
     * @Aspect 클래스의 메써드 레벨에 @AfterReturning을 명시하면 해당 메써드의 실행이 종료되어 값을 리턴할 때 끼어 들 수 있다.
     * 리턴 값을 확인할 수 있을 뿐 대상 메써드의 제어나 가공은 불가능하다.
     */
    @AfterReturning(pointcut = "execution(* com..example.*.*(..))", returning = "result")
    public void doSomethingAfterReturning(JoinPoint joinPoint, Object result) {
        // 끼어들어 실행할 로직을 작성
        System.out.println("AfterReturning start :: 모든 클래스에서 호출");
    }
}
