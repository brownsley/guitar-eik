package com.chord.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.chord.server.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ApplicationConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public ApplicationConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization")
                        .allowCredentials(false)
                        .maxAge(3600);
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
                .formLogin(login -> login.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/songs/**").permitAll()
                        .requestMatchers("/albums/**").permitAll()
                        .requestMatchers("/report/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/artists/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/health").permitAll()
                        .requestMatchers("/RPA/**").permitAll()
                        .requestMatchers("/songs").authenticated().anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
