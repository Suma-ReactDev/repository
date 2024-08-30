package io.demoprojects.dak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


public class SecurityConfig1 {

    
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .requestMatchers("/", "/index.html", "/static/**", "/api/public/**", "/h2-console/**","/*.js", "/*.css").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/")
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .and()
            .headers()
                .frameOptions().sameOrigin(); // Allow iframe from the same origin

        return http.build();
    }


    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                 .username("user")
                 .password("password")
                 .roles("USER")
                 .build();

        return new InMemoryUserDetailsManager(user);
    }

}
