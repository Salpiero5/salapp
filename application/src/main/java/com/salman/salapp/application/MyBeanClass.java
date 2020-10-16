package com.salman.salapp.application;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.*;

@Configuration
@Scope("prototype")
@NoArgsConstructor
public class MyBeanClass {

    private int a = 1;

    private MyBeanClass(int a) {
        this.a = a;
    }

    @Bean("myBean")
    public MyBeanClass myBeanClass() {
        return new MyBeanClass();
    }

    @Bean({"myInstance1","myInstance2"})
    @Profile("development")
    public MyBeanClass instance() {
        return new MyBeanClass(111);
    }

    @Bean({"myInstance1","myInstance2"})
    @Profile("production")
    public MyBeanClass instance2() {
        return new MyBeanClass(222);
    }

    @Override
    public String toString() {
        return "MyBeanClass{" +
                "a=" + a +
                '}';
    }
}
