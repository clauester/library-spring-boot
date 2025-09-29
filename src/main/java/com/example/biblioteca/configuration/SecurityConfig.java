package com.example.biblioteca.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(config -> config.disable())
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("/v1/user/create").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .httpBasic(withDefaults());          
                
        return httpSecurity.build();     
    }

    @Bean
    UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(org.springframework.security.core.userdetails.User
            .withUsername("miguel")
            .password("1234")
            .roles()
            .build());
        return manager;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception{
            return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
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
