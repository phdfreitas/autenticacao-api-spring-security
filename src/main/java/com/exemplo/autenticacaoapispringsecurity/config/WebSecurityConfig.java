package com.exemplo.autenticacaoapispringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain configureRoutes(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers("/users").hasAnyRole("USERS", "ADMIN")
                .antMatchers("/admin").hasAnyRole("ADMIN")
                .anyRequest().authenticated().and().formLogin();

        return http.build();
    }

    @Bean
    public UserDetailsService configure() throws Exception{
        InMemoryUserDetailsManager auth = new InMemoryUserDetailsManager();
        auth.createUser(User
                .withUsername("pedro")
                .password("{noop}pass123") // {noop} -> estrategia de criptografia
                .roles("USERS")
                .build());
        auth.createUser(User
                .withUsername("admin")
                .password("{noop}adm123")
                .roles("ADMIN")
                .build());

        return auth;
    }

}
