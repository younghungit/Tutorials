package com.younghun.library.config;

import com.younghun.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebConfig{

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Configure authorization rules
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/signup").permitAll()  // Allow login and signup without session
                        .anyRequest().authenticated()  // Require authentication for any other request
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Set the login page URL
                        .loginProcessingUrl("/login")  // Form submission URL
                        .defaultSuccessUrl("/home", true)  // Redirect after successful login
                        .permitAll()  // Allow everyone to access the login page
                )
                // Configure logout
                .logout(logout -> logout
                        .permitAll()  // Allow everyone to log out
                );  // Allow all users to log out

        return http.build();
    }
}