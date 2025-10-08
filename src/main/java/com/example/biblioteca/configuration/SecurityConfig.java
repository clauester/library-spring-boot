package com.example.biblioteca.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.biblioteca.configuration.filters.JwtAuthenticationFilter;
import com.example.biblioteca.configuration.jwt.JwtUtils;
import com.example.biblioteca.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailService userDetailService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);

        httpSecurity
                .csrf(config -> config.disable())
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("/v1/user/create").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilter(jwtAuthenticationFilter);       
                
        return httpSecurity.build();     
    }

    // @Bean
    // UserDetailsService userDetailsService(){
    //     InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    //     manager.createUser(org.springframework.security.core.userdetails.User
    //         .withUsername("miguel")
    //         .password("1234")
    //         .roles()
    //         .build());
    //     return manager;
    // }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception{
            return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder).and().build();
        }



    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    //     httpSecurity
    //             .csrf(config -> config.disable())
    //             .authorizeHttpRequests((authorize) -> {
    //                 authorize.requestMatchers("/v1/user/create").permitAll();
    //                 authorize.anyRequest().authenticated();
    //             })
    //             .formLogin(t -> {
                    
    //                 t.permitAll().successHandler(successHandler());
    //             })
    //             .sessionManagement(session -> {
    //                 session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
    //                     .invalidSessionUrl("/login")
    //                     .maximumSessions(1)
    //                     .expiredUrl("/login")
    //                     .sessionRegistry(sessionRegistry());
    //                 session.sessionFixation()
    //                     .migrateSession();
    //             })
    //             .httpBasic(withDefaults());          
                
    //     return httpSecurity.build();     
    // }

    // @Bean
    // public SessionRegistry sessionRegistry(){
    //     return new SessionRegistryImpl();
    // }

    // public AuthenticationSuccessHandler successHandler(){
    //     return ((request,response,authentication) -> {
    //         response.sendRedirect("/v1/user/session");
    //     });
    // }
}
