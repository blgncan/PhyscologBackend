package com.physcolog.security.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class WebSecurityConfig {
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable()) // ❌ CSRF kapalı
                .httpBasic(httpBasic -> httpBasic.disable()) // 🔥 HTTP Basic auth kapalı
                .formLogin(formLogin -> formLogin.disable()) // ⛔ Spring'in default login sayfasını kapat

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/public",
                                "/home",
                                "/contact/**",
                                "/contact-form",
                                "/contact-form/all",
                                "/contact-form/isApproved",
                                "/contact-form/isApproved/{contactFormId}",
                                "/contact-form/{contactFormId}",
                                "/about",
                                "/about/{aboutId}",
                                "/about/all",
                                "/slider",
                                "/slider/{sliderId}",
                                "/academic/**",
                                "/academic/all",
                                "/academic/{academicId}",
                                "/education/**",
                                "/education/all",
                                "/courses/**",
                                "/courses/all",
                                "/courses/{coursesId}",
                                "/clinics/all",
                                "/clinics/all-clinics",
                                "/clinics/latest-six",
                                "/clinics/{clinicId}",
                                "/videos",
                                "/videos/latest-four",
                                "/videos/category/{videoCategory}",
                                "/videos/{videoId}",
                                "/videos/{videoId}",
                                "/randevu",
                                "/randevu/all",
                                "/randevu/{randevuId}",
                                "/documents/latest-one",
                                "/documents/all",
                                "/documents/{documentsId}",
                                "/auth/login",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/upload/**",
                                "/static/**"
                        ).permitAll()
                        .requestMatchers("/admin/**").authenticated()
                        .anyRequest().authenticated()
                )

                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/home")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
