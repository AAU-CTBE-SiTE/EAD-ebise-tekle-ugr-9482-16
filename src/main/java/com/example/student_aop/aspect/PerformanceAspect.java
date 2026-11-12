
package com.example.student_aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

@Aspect
@Component
@Order(2)
public class PerformanceAspect {

    @Around("execution(* com.example.student_aop.service.StudentService.*(..))")
    public Object measureExecutionTime(
            ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.nanoTime();

        try {

            System.out.println("\n========== AROUND ADVICE ==========");
            System.out.println("Executing: " +
                    proceedingJoinPoint.getSignature().getName());

            // Continue to the actual target method
            return proceedingJoinPoint.proceed();

        } finally {

            long endTime = System.nanoTime();

            long duration = endTime - startTime;

            System.out.println("Execution time: " +
                    duration / 1_000_000.0 + " ms");
        }
    }
}

