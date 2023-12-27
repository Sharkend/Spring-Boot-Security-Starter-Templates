package com.sharkend.jwttemplate.Config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.sharkend.jwttemplate.Entity.User.Permission.*;
import static com.sharkend.jwttemplate.Entity.User.Role.ADMIN;
import static com.sharkend.jwttemplate.Entity.User.Role.MANAGER;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity //for @PreAuthorize at controller level, @EnableGlobalMethodSecurity(prePostEnabled = true) for older spring versions
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //todo upgrade deprecated functions
        http
                .csrf().disable()
                .authorizeHttpRequests()

                .requestMatchers("/api/v1/auth/**",
                //Swagger:
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**"
                )
                    .permitAll()

                /**Securing management using requestMatchers**/
                //Secure entire endpoint > roles
                .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                //Secure specific methods of an endpoint > permissions
                .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())

                /**Securing admin using annotations in the controller, instead of code below**/
//                .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
//                .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
//                .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
//                .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
//                .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())

                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout") //default /logout
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) ->
                        SecurityContextHolder.clearContext())
                ;
        return http.build();
    }
}
