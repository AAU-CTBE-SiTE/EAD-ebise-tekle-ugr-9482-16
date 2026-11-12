
package com.example.student_aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

@Aspect
@Component
@Order(3)
public class LoggingAspect {

    // Reusable pointcut
    @Pointcut("execution(* com.example.student_aop.service.StudentService.*(..))")
    public void studentServiceMethods() {
    }

    // Executes before the target method
    @Before("studentServiceMethods()")
    public void logBefore(JoinPoint joinPoint) {

        System.out.println("\n========== BEFORE ADVICE ==========");
        System.out.println("Method: " +
                joinPoint.getSignature().getName());

        System.out.println("Arguments: " +
                java.util.Arrays.toString(joinPoint.getArgs()));
    }

    // Executes after the target method finishes
    @After("studentServiceMethods()")
    public void logAfter(JoinPoint joinPoint) {

        System.out.println("========== AFTER ADVICE ==========");
        System.out.println("Finished: " +
                joinPoint.getSignature().getName());
    }

    // Executes only after successful completion
    @AfterReturning(
            pointcut = "studentServiceMethods()",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        System.out.println("========== AFTER RETURNING ==========");
        System.out.println("Method: " +
                joinPoint.getSignature().getName());

        System.out.println("Result: " + result);
    }
}

