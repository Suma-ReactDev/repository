package io.demoprojects.dak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;


public class SecurityConfig1 {

    
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .requestMatchers("/", "/index.html", "/static/**", "/api/public/**", "/h2-console/**","/*.js", "/*.css").permitAll()
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage("/")
//                .permitAll()
//                .and()
//            .logout()
//                .permitAll()
//                .and()
//            .headers()
//                .frameOptions().sameOrigin(); // Allow iframe from the same origin
//
//        return http.build();
//    }

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	    http
//	        .csrf().disable() // Consider enabling if using sessions with forms
//	        .authorizeRequests()
//	            .requestMatchers("/", "/index.html", "/static/**", "/api/public/**").permitAll()
//	            .requestMatchers("/api/auth/**").permitAll()
//	            .anyRequest().authenticated()
//	        .and()
//	        .formLogin()
//	            .loginPage("/") // Ensure this maps correctly to your React app
//	            .permitAll()
//	        .and()
//	        .logout()
//	            .permitAll()
//	        .and()
//	        .headers()
//	            .frameOptions().sameOrigin()
//	        .and()
//	        .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//            .invalidSessionUrl("/") // Redirect to signin on invalid session
//            .maximumSessions(1) // Allow only one session per user
//            .expiredUrl("/"); 
//	    return http.build();
//	}

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for API endpoints. Enable it if using forms.
            .authorizeRequests()
                .requestMatchers("/", "/index.html", "/static/**", "/api/public/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/") // Ensure this maps correctly to your React app
                .permitAll()
            .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/") // Redirect to home or login after logout
                .invalidateHttpSession(true) // Invalidate the session on logout
                .deleteCookies("JSESSIONID") // Remove the JSESSIONID cookie on logout
            .and()
            .headers()
                .frameOptions().sameOrigin() // Allow if using frames within same origin
            .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Create sessions only when required
                .invalidSessionUrl("/") // Redirect to home or login if session is invalid
                .maximumSessions(1) // Limit to one session per user
                .expiredUrl("/") ;// Redirect to home or login if session expires

        return http.build();
    }
    
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher(); // Bean to handle session events
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
