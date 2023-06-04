package com.hust.nhakhoa.Config;

import com.hust.nhakhoa.Exceptions.UnauthenticatedRequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig  {


    private final AuthenticationProvider authenticationProvider;

    private final JwtAthFilter jwtAuthFilter;
    private final UnauthenticatedRequestHandler unauthenticatedRequestHandler;

    private static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/v3/api-docs",
            "/swagger-ui.html",
            "/api-docs/**",
            "api-docs",
            "api/medicine/all",
            "api/medicine/add"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthenticatedRequestHandler)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**", "/error")
                .permitAll()
                .requestMatchers(SWAGGER_WHITELIST)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
////                .exceptionHandling()
// //               .authenticationEntryPoint(unauthenticatedRequestHandler)
// //               .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/api/auth/**", "/error")
//                .permitAll()
//                .requestMatchers(SWAGGER_WHITELIST)
//                .permitAll()
//                .requestMatchers("/api/nhakhoa/**","/api/medicine/**","/api/patient/**",
//                        "/api/prescription/**","/api/users/**", "/api/doctor/**" ).permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
    }




}
