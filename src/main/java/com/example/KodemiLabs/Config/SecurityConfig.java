//package com.example.KodemiLabs.Config;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
////    @Autowired
////    private MyUserDetailsService userDetailsService;
////
////    @Autowired
////    private JwtFilter jwtFilter;
////
////    public SecurityConfig(MyUserDetailsService userDetailsService) {
////        this.userDetailsService = userDetailsService;
////    }
//
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http){
//        http.csrf(customizer -> customizer.disable())
//                .authenticationProvider(authenticationProvider())
//                .authorizeHttpRequests(request -> request
//
//                        //Assigning the Roles who can Access what
//                        .requestMatchers("/user/login").permitAll()
//                        .requestMatchers("/user/register").permitAll()
//
//                        .requestMatchers(HttpMethod.GET, "/api/employees/**")
//                        .hasAnyRole("USER", "ADMIN")
//
//                        .requestMatchers(HttpMethod.GET, "/api/departments/**")
//                        .hasAnyRole("USER", "ADMIN")
//
//
//                        .requestMatchers(HttpMethod.POST, "/api/employees/**")
//                        .hasRole("ADMIN")
//
//                        .requestMatchers(HttpMethod.PUT, "/api/employees/**")
//                        .hasRole("ADMIN")
//
//                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**")
//                        .hasRole("ADMIN")
//
//                        .requestMatchers(HttpMethod.POST, "/api/departments/**")
//                        .hasRole("ADMIN")
//                        .anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(ssession->
//                        ssession.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//
//                //Added JWT Filter before UsernamePasswordAuthenticationFilter
////
////                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//        return  http.build();
//    }
//
//
//
//
////    @Bean
////    public AuthenticationProvider authenticationProvider() {
////        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
////        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
////        return provider;
////    }
//
//
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return  config.getAuthenticationManager();
//    }
//
//
//
//}