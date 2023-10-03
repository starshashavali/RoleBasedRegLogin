package com.org.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
	

	    @NotBlank(message = "Username is required")
	    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	    private String userName;

	    @NotBlank(message = "User email is required")
	    @Email(message = "Invalid email format")
	    private String userEmail;

	    @NotBlank(message = "Password is required")
	    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
	    private String userPwd;

	    @NotNull(message = "Age cannot be null")
	    @Min(value = 18, message = "Age must be at least 18")
	    @Max(value = 120, message = "Age must not exceed 120")
	    private Integer age;

	    @NotBlank(message = "Address is required")
	    private String address;
	    

	}



