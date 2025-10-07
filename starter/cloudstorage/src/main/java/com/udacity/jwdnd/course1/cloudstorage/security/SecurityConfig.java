package com.udacity.jwdnd.course1.cloudstorage.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;

// allows Spring configuration to be loaded into "ApplicationContext" & used throughout application
@Configuration

// finds configuration & applies for global WebSecurity of application; provides Spring MVC integration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(AuthenticationService authenticationService, PasswordEncoder passwordEncoder) {
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // allows FREE ACCESS to sign-up page, CSS files, and JavaScript files, any OTHER Request Must Be authenticated (i.e., user must be logged in to access)
        http
            .authorizeRequests()
                .antMatchers("/signup", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();

        // MUST USE "http" NOT ".and()" or Undefined ".defaultSuccessUrl()" Error will Occur
        http.formLogin(form -> form
            .loginPage("/login")
            // Redirects to "home" page after confirming user authentication
            .defaultSuccessUrl("/home", true)
        );
        
        // MUST USE "http" NOT ".and()" or Undefined ".and()" Error will Occur
        http.logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Injects Custom Authentication Provider into "AuthenticationManagerBuilder" in Spring Security configuration; Allows Username & Password to be Compared
        auth.authenticationProvider(this.authenticationService);
    }
}