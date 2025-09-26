package com.udacity.jwdnd.course1.cloudstorage.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// allows Spring configuration to be loaded into "ApplicationContext" & used throughout application
@Configuration

// finds configuration & applies for global WebSecurity of application; provides Spring MVC integration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // allows FREE ACCESS to sign-up page, CSS files, and JavaScript files, any OTHER Request Must Be authenticated (i.e., user must be logged in to access)
        http
            .authorizeRequests()
                .antMatchers("/signup", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
            .and()
                // Redirects to "home" page after confirming user authentication
                .defaultSuccessUrl("/home", true)
            .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Injects Custom Authentication Provider into "AuthenticationManagerBuilder" in Spring Security configuration; Allows Username & Password to be Compared
        auth.authenticationProvider(this.authenticationService);
    }
}