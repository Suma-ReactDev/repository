package io.demoprojects.dak.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.demoprojects.dak.dto.LoginRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
private final AuthenticationManager authenticationManager;
	
    @Autowired
    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
	 @PostMapping("/signin")
	    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDto loginRequest, HttpServletRequest request) {
	        Map<String, Object> response = new HashMap<>();
	        try {
	            UsernamePasswordAuthenticationToken authToken =
	                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

	            // Authenticate the user
	            Authentication authentication = authenticationManager.authenticate(authToken);
	            SecurityContextHolder.getContext().setAuthentication(authentication);

	            // Create a session and store the authentication object
	            HttpSession session = request.getSession(true);
	            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

	            // Get the authenticated user's details
	            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

	            // Prepare JSON response
	            response.put("message", "Login successful, session created.");
	            response.put("username", userDetails.getUsername());
	            // Add any other user details you want to include
	            System.out.println("Signin successful: " + response);

	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	            response.put("error", "Invalid username or password.");
	            System.out.println("Signin error: " + e.getMessage());
	            return ResponseEntity.status(401).body(response);
	        }
	    }


//	    @PostMapping("/signout")
//	    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
//	        HttpSession session = request.getSession(false);
//	        if (session != null) {
//	            session.invalidate(); // Invalidate the session to log the user out
//	        }
//	        SecurityContextHolder.clearContext();
//	        return ResponseEntity.ok("Logout successful, session invalidated.");
//	    }
	    
	    @PostMapping("/signout")
	    public ResponseEntity<Map<String, Object>> logout(HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate();
	        }
	        SecurityContextHolder.clearContext();
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", "Logout successful");
	        System.out.println("Signout successful");
	        return ResponseEntity.ok(response);
	    }
	    
	    @GetMapping("/status")
     public ResponseEntity<Map<String, Object>> checkAuthStatus() {
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         Map<String, Object> response = new HashMap<>();
         response.put("isAuthenticated", auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken));
         response.put("user", auth != null ? auth.getName() : null);
         System.out.println("Auth status checked: " + response);

         return ResponseEntity.ok(response);
     }
}
