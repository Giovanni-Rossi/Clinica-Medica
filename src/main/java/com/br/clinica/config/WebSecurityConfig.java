package com.br.clinica.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.br.clinica.config.CustomAuthenticationSuccessHandler;
import com.br.clinica.security.UsuarioDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig { 
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
    
    @Bean
    public UserDetailsService clienteDetailsService() {
        return new UsuarioDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider clienteAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(clienteDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    } 
    
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                    .requestMatchers("/error", "/login/**", "/js/**").permitAll()
                    .requestMatchers("/css/**", "/image/**", "/webjars/**").permitAll()
                    .requestMatchers("/clientes", "/locadoras", "/locacoes").permitAll()
                    .requestMatchers("/medicos", "medicos/{d}","/medicos/especialidade/{d}").permitAll()
                    .requestMatchers("/clientes/{d}", "/locadoras/{d}").permitAll()
                    .requestMatchers("/locacoes/{d}").permitAll()
                    .requestMatchers("/locadoras/cidade/{w}").permitAll()
                    .requestMatchers("/locacoes/clientes/{d}").permitAll()
                    .requestMatchers("/locacoes/locadoras/{d}").permitAll()
                    .anyRequest().authenticated())
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin((form) -> form
                    .loginPage("/login")
                    .successHandler(customAuthenticationSuccessHandler)
                    .permitAll())
            .logout((logout) -> logout
                    .logoutSuccessUrl("/").permitAll());

        return http.build();
    }
    
    }

