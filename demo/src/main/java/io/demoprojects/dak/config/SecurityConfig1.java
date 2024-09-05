package io.demoprojects.dak.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletResponse;


public class SecurityConfig1 {

    
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .requestMatchers("/", "/index.html", "/static/**", "/api/public/**").permitAll()
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

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http
	        .cors(Customizer.withDefaults())
	        .authorizeHttpRequests(authorize -> authorize
	            .requestMatchers("/", "/index.html", "/static/**", "/api/public/**").permitAll()
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	            .loginPage("/api/auth/signin")
	            .loginProcessingUrl("/api/auth/signin")
	            .successHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK))
	            .failureHandler((request, response, exception) -> response.setStatus(HttpServletResponse.SC_UNAUTHORIZED))
	            .permitAll()
	        )
	        .logout(logout -> logout
	            .logoutUrl("/api/auth/signout")
	            .logoutSuccessHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK))
	            .permitAll()
	        )
	        .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
	        .csrf(AbstractHttpConfigurer::disable)
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
	        .build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("http://192.168.0.104:8080")); // Add your React app's URL
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    configuration.setAllowCredentials(true);
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
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
//
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable() // Disable CSRF for API endpoints. Enable it if using forms.
//            .authorizeRequests()
//                .requestMatchers("/**", "/index.html", "/static/**", "/api/public/**").permitAll()
//                .anyRequest().authenticated()
//            .and()
//            .formLogin()
//                .loginPage("/") // Ensure this maps correctly to your React app
//                .permitAll()
//            .and()
//            .logout()
//                .permitAll()
//                .logoutSuccessUrl("/") // Redirect to home or login after logout
//                .invalidateHttpSession(true) // Invalidate the session on logout
//                .deleteCookies("JSESSIONID") // Remove the JSESSIONID cookie on logout
//            .and()
//            .headers()
//                .frameOptions().sameOrigin() // Allow if using frames within same origin
//            .and()
//            .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Create sessions only when required
//                .invalidSessionUrl("/") // Redirect to home or login if session is invalid
//                .maximumSessions(1) // Limit to one session per user
//                .expiredUrl("/") ;// Redirect to home or login if session expires
//
//        return http.build();
//    }
    
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
