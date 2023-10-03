package com.org.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.domain.UserEntity;
import com.org.dto.LoginRequest;
import com.org.dto.RegistrationRequest;
import com.org.service.MyUserDetailsServiceImpl;

@RestController
@Valid
public class AdminRestController {
	
	
	@Autowired
	MyUserDetailsServiceImpl service;

	@PostMapping("/userSave")
	public ResponseEntity<?> saveUser(@Valid @RequestBody RegistrationRequest request) {
		String saveUser = service.saveUser(request);
		return new ResponseEntity<>(saveUser, HttpStatus.CREATED);

	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest request) {
		String loginUser = service.loginUser(request);
		return new ResponseEntity<>(loginUser, HttpStatus.OK);
	}
	@PatchMapping("/EnableOrDisable/{id}")
	public ResponseEntity<?> switchUserActiveOrDeactive(@Valid @PathVariable Integer id) {
		String switchToActiveorDeActive = service.switchToActiveorDeActive(id);
		return new ResponseEntity<>(switchToActiveorDeActive, HttpStatus.OK);

	}

	

	@GetMapping("/getAllUserRole")
	public ResponseEntity<?> getAllUserRole() {
	 List<UserEntity> allUsersWithUserRole = service.getAllUsersWithUserRole();
		return new ResponseEntity<>(allUsersWithUserRole, HttpStatus.OK);
		
	}
	@GetMapping("/getAllUserActive")
	public ResponseEntity<?> getAllUserActive() {
	 List<UserEntity> allUsersIsActive = service.getAllUsersIsActive();
		return new ResponseEntity<>(allUsersIsActive, HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllIn-ActiveUser")
	public ResponseEntity<?> getAllInActiveUsers() {
	 List<UserEntity> allUsersIsInactive = service.getAllUsersIsInactive();
		return new ResponseEntity<>(allUsersIsInactive, HttpStatus.OK);
	}
}
