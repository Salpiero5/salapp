package com.salman.salapp.application.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth
                .inMemoryAuthentication()
                .withUser("Salman")
                .password(passwordEncoder().encode(" "))
                .roles("ADMIN")
                .and()
                .withUser("sara")
                .password(passwordEncoder().encode(" "))
                .roles("USER");
    }

    /**
     * When you @Override this method, you should specific all urls in your application for having
     * security login, roles, etc
     * @Secured in controller layer overrides this hasRole()
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/customers/welcome").permitAll()
                .antMatchers("/h2/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/login*").permitAll()
                .and()
                .formLogin();

        //This is for h2 configurations
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

//    /**
//     * For disabling the login page not the secured roles
//     * @param httpSecurity
//     * @throws Exception
//     */
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.httpBasic().disable();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
