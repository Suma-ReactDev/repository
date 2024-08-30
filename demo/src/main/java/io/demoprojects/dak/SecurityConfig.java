package io.demoprojects.dak;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((authorize) -> authorize.anyRequest().fullyAuthenticated())
//				.formLogin();
//
//		return http.build();
//	}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeRequests()
            .requestMatchers("/", "/index.html", "/assets/**", "/api/**").permitAll() // Ensure static resources are accessible
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .defaultSuccessUrl("/dashboard", true)
                .permitAll()
            .and()
            .logout()
                .permitAll();

        return http.build();
    }
//	@Bean
//	public LdapTemplate ldapTemplate() {
//		return new LdapTemplate(contextSource());
//	}

//	@Bean
//	public LdapContextSource contextSource() {
//		LdapContextSource ldapContextSource = new LdapContextSource();
//		.userSearchBase("DC=as1,DC=net")
//		.userSearchFilter("(&(objectClass=user)(sAMAccounName={0}))")
//		.ldapAuthoritiesPopulator(ldapAuthoritiesPopulator())
//		.contextSource()
//		.url("ldap:xxxxx")
//		.managerDn("cn=ldap,CN=User,DC=my,DC=app")
//		.managerPassword("reactuser");
//
////		ldapContextSource.setUrl("ldap://localhost:10389");
////		ldapContextSource.setUserDn("uid=admin,ou=system");
////		ldapContextSource.setPassword("secret");
//		return ldapContextSource;
//	}

//	@Bean
//    public DefaultSpringSecurityContextSource ldapContextSource() {
//        DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource("ldap://localhost:8389");
//        contextSource.setUserDn("cn=ldap,CN=User,DC=act,DC=fibernet");
//        contextSource.setPassword("reactuser");
//        contextSource.setBase("DC=act,DC=fibernet");
//
//        // Optional: Configure referral handling
//        contextSource.setReferral("follow");
//
//        // Connect and initialize the context source
//        contextSource.afterPropertiesSet();
//        
//        return contextSource;
//    }
//	@Bean
//	AuthenticationManager authManager(BaseLdapPathContextSource source) {
//		LdapBindAuthenticationManagerFactory factory = new LdapBindAuthenticationManagerFactory(source);
//		factory.setUserDnPatterns("cn={0},ou=users,ou=system");
//		return factory.createAuthenticationManager();
//	}
	
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); // Replace with your React app's URL
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	 @Bean
	    public UserDetailsService userDetailsService() {
	        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	        manager.createUser(User.withUsername("user").password("{noop}password").roles("USER").build());
	        manager.createUser(User.withUsername("admin").password("{noop}admin").roles("ADMIN").build());
	        return manager;
	    }
}