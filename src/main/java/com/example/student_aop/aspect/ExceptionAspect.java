
package com.example.student_aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

@Aspect
@Component
@Order(4)
public class ExceptionAspect {

    @AfterThrowing(
            pointcut = "execution(* com.example.student_aop.service.StudentService.*(..))",
            throwing = "exception"
    )
    public void logException(
            JoinPoint joinPoint,
            Throwable exception) {

        System.out.println("\n========== EXCEPTION ASPECT ==========");

        System.out.println("Method: " +
                joinPoint.getSignature().getName());

        System.out.println("Exception: " +
                exception.getMessage());
    }
}

