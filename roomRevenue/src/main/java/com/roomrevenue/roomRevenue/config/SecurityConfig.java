package com.roomrevenue.roomRevenue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/", "/public/**").permitAll() // Public routes
                                .antMatchers("/gerente-general/**",
                                        "/gestionar-personal-div-cuartos/**",
                                        "/gestionar-personal-gerente-reservas/**",
                                        "/gestionar-personal-recepcionista/**",
                                        "/gestionar-reservas-list/**",
                                        "/add-reserva/**",
                                        "/add-reserva-asignar-habitacion/**",
                                        "/add-reserva-asignar-cliente/**",
                                        "/list-habitacion-reserva/**",
                                        "/list-huespedes-reserva/**",
                                        "/gerente-general-gestion-habitaciones/**")
                                .hasAnyRole("GERENTE_GENERAL", "GERENTE_DIVISION_CUARTOS", "GERENTE_RESERVACIONES", "RECEPCIONISTA")
                                .antMatchers("/recepcion-general/**",
                                        "/check-in/**",
                                        "/check-out/**").hasRole("RECEPCIONISTA")
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .permitAll()
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails gerenteGeneral = User.withUsername("gerente_general")
                .password(passwordEncoder().encode("password"))
                .roles("GERENTE_GENERAL")
                .build();

        UserDetails gerenteDivisionCuartos = User.withUsername("gerente_division_cuartos")
                .password(passwordEncoder().encode("password"))
                .roles("GERENTE_DIVISION_CUARTOS")
                .build();

        UserDetails gerenteReservaciones = User.withUsername("gerente_reservaciones")
                .password(passwordEncoder().encode("password"))
                .roles("GERENTE_RESERVACIONES")
                .build();

        UserDetails recepcionista = User.withUsername("recepcionista")
                .password(passwordEncoder().encode("password"))
                .roles("RECEPCIONISTA")
                .build();

        return new InMemoryUserDetailsManager(gerenteGeneral, gerenteDivisionCuartos, gerenteReservaciones, recepcionista);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
