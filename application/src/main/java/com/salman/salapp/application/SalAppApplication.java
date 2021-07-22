package com.salman.salapp.application;

import com.salman.salapp.application.config.FileStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.salman.salapp.application", "com.salman.salapp.library"})
@EntityScan("com.salman.salapp.library.entity")
@EnableConfigurationProperties({
        FileStorageConfig.class
})
@EnableJpaRepositories
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SalAppApplication {

    public static void main(String[] args) {
//        ApplicationContext context =
                SpringApplication.run(SalAppApplication.class, args);

//        /**
//         * For printing one bean class
//         */
//        System.out.println(context.getBean("securityConfig").toString());
//        System.out.println(context.getBean("myInstance1").toString());

//        /**
//         * For printing all beans
//         */
//        String[] beans = context.getBeanDefinitionNames();
//        Arrays.sort(beans);

        /*for (String s : beans) {
            System.out.println(s);
        }*/
    }
}
