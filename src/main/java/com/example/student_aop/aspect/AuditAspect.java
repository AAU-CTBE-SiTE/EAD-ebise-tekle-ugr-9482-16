
package com.example.student_aop.aspect;

import com.example.student_aop.annotation.TrackExecution;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.example.student_aop.annotation.Auditable;
import org.springframework.aop.aspectj.annotation.*;

@Aspect
@Component
@Order(1)
public class AuditAspect {

    @Pointcut("@annotation(com.example.student_aop.annotation.TrackExecution)")
    public void trackedMethods() {
    }


    @Before("trackedMethods()")
    public void auditOperation(JoinPoint joinPoint) {

        System.out.println("\n========== AUDIT ASPECT ==========");

        System.out.println("Tracked operation: " +
                joinPoint.getSignature().getName());

        System.out.println("Audit record: Operation initiated");
    }
}

