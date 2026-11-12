package com.example.student_aop.aspect;

import com.example.student_aop.annotation.Auditable;

public class AuditableImpl implements Auditable {

    @Override
    public String getAuditDescription() {

        return "StudentService supports auditing through AOP introduction.";

    }
}
