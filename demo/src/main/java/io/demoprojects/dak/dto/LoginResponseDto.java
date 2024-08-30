package io.demoprojects.dak.dto;

public class LoginResponseDto {
	
	private String token;
	private String message;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LoginResponseDto(String token, String message) {
		super();
		this.token = token;
		this.message = message;
	}
	 
	 

}
