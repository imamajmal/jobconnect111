package com.example.jobproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.jobproject.service.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

      // ✅ STEP 1 — Inject your custom UserDetailsService
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF for form submissions
            .csrf(csrf -> csrf.disable())

            // Authorization configuration
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/jobs/**").hasAuthority("EMPLOYER")
                .requestMatchers("/api/jobseeker/**").hasAuthority("JOB_SEEKER")
                .requestMatchers("/api/sms/**").hasAnyAuthority("EMPLOYER", "JOB_SEEKER")
                .requestMatchers("/", "/register", "/login", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/jobs/employer/**").hasAuthority("EMPLOYER")
                .requestMatchers("/jobs/all").hasAnyAuthority("EMPLOYER", "JOB_SEEKER")
                .requestMatchers("/sms/**").hasAnyAuthority("EMPLOYER", "JOB_SEEKER")

                .anyRequest().authenticated()
            )

           .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("username")        // ✅ Input field name
                .passwordParameter("password")        // ✅ Input field name
                .defaultSuccessUrl("/dashboard", true) // ✅ Redirect to dashboard
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

             // ✅ Connect authentication provider
        http.authenticationProvider(daoAuthenticationProvider());

        return http.build();
    }

      // ✅ Use your custom UserDetailsService
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication Manager (required for authentication)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}




