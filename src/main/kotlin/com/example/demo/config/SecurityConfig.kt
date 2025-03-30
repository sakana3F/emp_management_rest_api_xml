// package com.example.demo.config

// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
// import org.springframework.security.config.annotation.web.WebSecurityConfigurer
// import org.springframework.security.config.annotation.web.builders.HttpSecurity
// import org.springframework.security.web.SecurityFilterChain
// import org.springframework.context.annotation.Bean

// @Configuration
// @EnableWebSecurity
// class SecurityConfig {

//     @Bean
//     fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//         http
//             .authorizeHttpRequests {
//                 it
//                     .mvcMatchers("/administrator").permitAll()
//                     .mvcMatchers("/administrator/**").hasRole("ADMIN")
//                     .anyRequest().authenticated()
//             }
//             .formLogin { it.defaultSuccessUrl("/administrator/{id}", true) }
//             .logout { it.logoutSuccessUrl("/ex-emp-api") }
//             .csrf { it.disable() } // CSRF 対策機能を無効にする設定 (REST APIの場合は無効化)
//             .formLogin()
        
        
        
//             // return http.build()
//     }
// }
