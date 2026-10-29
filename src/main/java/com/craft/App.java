package com.craft;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("beansConfig.xml");

        Student student = context.getBean("student", Student.class);
        student.getDetails();

        Subject math1 = context.getBean("math", Subject.class);
        Subject math2 = context.getBean("math", Subject.class);

        System.out.println("Same singleton object? " + (math1 == math2));

        Subject physics1 = context.getBean("physics", Subject.class);
        Subject physics2 = context.getBean("physics", Subject.class);

        System.out.println("Same prototype object? " + (physics1 == physics2));
    }
}
