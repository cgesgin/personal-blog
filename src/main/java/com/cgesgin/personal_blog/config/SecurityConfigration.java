package com.cgesgin.personal_blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((req) -> req 
        
        .requestMatchers("/new").authenticated()
        .requestMatchers("/edit/**").authenticated()
        .requestMatchers("/delete/**").authenticated()
        .requestMatchers("/admin").authenticated()
        .anyRequest().permitAll())
        .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
