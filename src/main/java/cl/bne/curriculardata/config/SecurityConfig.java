// src/main/java/cl/bne/curriculardata/api/SecurityConfig.java
package cl.bne.curriculardata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/health", "/v3/api-docs/**", "/swagger-ui/**", "/api/v1/**").permitAll()
        .anyRequest().permitAll());
    return http.build();
  }
}