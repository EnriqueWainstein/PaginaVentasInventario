package com._ip.pagina.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Configuracion_de_seguridad {
	
	
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // puede acceder cualquiera
                .requestMatchers("/h2-console/**", "/registrarse", "/public/**").permitAll()
                // Todo lo demás requiere login
                .anyRequest().authenticated()
            )
            // TODO crear vista
            .formLogin(form -> form
                .loginPage("/login") 
                .permitAll()
            )
            
            .logout(logout -> logout.permitAll());

        // TODO cambiar cuando termine SI o SI 
        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
	
	//recomendacion de IA TODO ver si esta bien o modificar si es necesario
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

}
