package com.example.salesmanagement.entity.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.security.web.authentication.logout.LogoutHandler;
// import org.springframework.security.core.context.SecurityContextHolder;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthfilter;
    private final AuthenticationProvider authenticationProvider;
    // private final LogoutHandler logoutHandler;
    
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http
        //     .cors()
        //         .and()
        //     .csrf()
        //         .disable()
        //     .sessionManagement()
        //         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //         .and()
        //     .authorizeRequests()   
        //         .antMatchers("/",
        //                     "/favicon.ico",
        //                     "/**/*.png",
        //                     "/**/*.gif",
        //                     "/**/*.svg",
        //                     "/**/*.jpg",
        //                     "/**/*.html",
        //                     "/**/*.css",
        //                     "/**/*.js")
        //                     .permitAll()
        //         .antMatchers("/api/auth/**","login")
        //             .permitAll()
        //         .antMatchers("/api/categories/**","/api/user/**","/api/product/**","/api/cartitems/**")
        //             .permitAll()
        //         .anyRequest()
        //             .authenticated()
        //         .and()
        //     .authenticationProvider(authenticationProvider)
        //     .addFilterBefore(jwtAuthfilter, UsernamePasswordAuthenticationFilter.class)
        //     .logout()
        //     .logoutSuccessUrl("/index.html")
        //     .addLogoutHandler(logoutHandler)
        //     .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
        http.cors();
        http.csrf(csrf -> csrf.disable())
        .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
        .mvcMatchers("/api/auth/**","/home/**").permitAll() 
        .antMatchers("/api/categories/**","/api/user/**","/api/product/**","/api/cartitems/**","/api/cart/***")
            .permitAll()
        .anyRequest().authenticated())

        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthfilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();    
    }

    
}
