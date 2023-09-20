package com.pokemonreview.api.security;

import com.pokemonreview.api.controllerEndPoints.BlackListControllerEndPoints;
import com.pokemonreview.api.controllerEndPoints.WhiteListControllerEndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.beans.Encoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JWTEntryPoint authEntryPoint;

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(authEntryPoint)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            .requestMatchers(WhiteListControllerEndPoints.whiteList()).permitAll()
            .requestMatchers(BlackListControllerEndPoints.blackControllers()).authenticated()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
    http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();

}

@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
}


@Bean
    public PasswordEncoder passwordEncoder() {
     return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter authenticationFilter() {
    return new JWTAuthenticationFilter();
    }
}
