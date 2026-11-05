package com.craft;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public Address address() {
        Address address = new Address();
        address.setCity("Addis Ababa");
        address.setStreet("Bole");
        return address;
    }

    @Bean
    @Scope("singleton")
    public Subject math() {
        Subject s = new Subject();
        s.setName("Mathematics");
        return s;
    }

    @Bean
    @Scope("prototype")
    public Subject physics() {
        Subject s = new Subject();
        s.setName("Physics");
        return s;
    }

    @Bean
    public Subject programming() {
        Subject s = new Subject();
        s.setName("Programming");
        return s;
    }

    @Bean
    public Student student() {
        Student student = new Student(
                1,
                "Abebe",
                20,
                address()
        );

        student.setSubjects(
                java.util.Arrays.asList(math(), physics(), programming())
        );

        return student;
    }
}
