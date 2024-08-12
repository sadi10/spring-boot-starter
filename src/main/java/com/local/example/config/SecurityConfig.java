package com.local.example.config;

import com.local.example.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        System.out.println(userDetailsService.loadUserByUsername(http.g));
        http
                .csrf().disable()  // Disable CSRF for REST APIs (consider enabling it for production with proper configuration)
                .authorizeHttpRequests()
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/private/api/**").permitAll()
                .requestMatchers("/addProduct/**").hasRole("MANAGER")// Allow access to public endpoints
//                .requestMatchers("/addProduct/**").permitAll()// Allow access to public endpoints
                .anyRequest().authenticated()  // Require authentication for all other endpoints
                .and()
                .httpBasic();  // Use HTTP Basic authentication for REST APIs

        return http.build();
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        var user1 = User.withUsername("user1")
                .password(passwordEncoder().encode("password1"))
                .roles("USER")
                .build();

        var user2 = User.withUsername("admin")
                .password(passwordEncoder().encode("adminpass"))
                .roles("ADMIN")
                .build();

        var user3 = User.withUsername("manager")
                .password(passwordEncoder().encode("managerpass"))
                .roles("MANAGER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }*/
    /*@Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }
    */@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCrypt for password encoding
    }
}