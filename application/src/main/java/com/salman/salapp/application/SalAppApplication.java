package com.salman.salapp.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@ComponentScan({"com.salman.salapp.application", "com.salman.salapp.library"})
@EntityScan("com.salman.salapp.library.entity")
@SpringBootApplication
public class SalAppApplication {

    public static void main(String[] args) {
        ApplicationContext context =
                SpringApplication.run(SalAppApplication.class, args);

        /**
         * For printing one bean class
         */
        System.out.println(context.getBean("securityConfig").toString());
//        System.out.println(context.getBean("myInstance1").toString());

        /**
         * For printing all beans
         */
//        String[] beans = context.getBeanDefinitionNames();
//        Arrays.sort(beans);

        /*for (String s : beans) {
            System.out.println(s);
        }*/
    }
}
