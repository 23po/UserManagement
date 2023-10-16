package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import lombok.RequiredArgsConstructor;

//import com.mysql.cj.protocol.AuthenticationProvider;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final jwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    // private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("api/v1/auth/**", "/home", "/")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                // .formLogin((form) -> form
                //                 .loginPage("/login")
                //                 .permitAll()
                // )
                // .logout((logout) ->
                //         logout.logoutUrl("/api/v1/auth/logout")
                //           .addLogoutHandler(logoutHandler)
                //           .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                // );

       return http.build();
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails user =
    //         User.withDefaultPasswordEncoder()
    //             .username("user")
    //             .password("password")
    //             .roles("USER")
    //             .build();
        
    //     return new InMemoryUserDetailsManager(user);
    // }
    
}