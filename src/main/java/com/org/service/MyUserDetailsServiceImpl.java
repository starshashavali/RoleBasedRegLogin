package com.org.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.domain.RoleEntity;
import com.org.domain.UserEntity;
import com.org.dto.LoginRequest;
import com.org.dto.RegistrationRequest;
import com.org.exception.DuplicateEmailException;
import com.org.exception.InvalidCredentialsException;
import com.org.exception.InvalidUserException;
import com.org.repo.RoleRepo;
import com.org.repo.UserRepo;

@Service
public class MyUserDetailsServiceImpl {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	public String saveUser(RegistrationRequest request) {
		UserEntity email = userRepo.findByUserEmail(request.getUserEmail());
		if (email != null) {
			throw new DuplicateEmailException("duplicate email...");
		}
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(request, entity);

		RoleEntity roleEntity = roleRepo.findByRoleName("USER");

		if (roleEntity != null) {
			RoleEntity role = new RoleEntity();

			role.setRoleId(roleEntity.getRoleId());
			role.setRoleName(roleEntity.getRoleName());
			entity.setRole(role);
			entity.setActive(true);
			userRepo.save(entity);

		}
		return "success";

	}

	public String switchToActiveorDeActive(Integer id) {
		Optional<UserEntity> findById = userRepo.findById(id);
		if (findById.isPresent()) {
			UserEntity userEntity = findById.get();
			userEntity.setActive(!userEntity.isActive());
			userRepo.save(userEntity);
			return "successfully switch";
		}
		return "fail to switch";

	}

	public String loginUser(LoginRequest request) {
		UserEntity entity = userRepo.findByUserEmailAndUserPwd(request.getUserEmail(), request.getUserPwd());

		if (entity == null) {
			throw new InvalidCredentialsException("Invalid credentials...");
		}
		if (!entity.isActive()) {
			throw new InvalidUserException("Invalid User credentials...");
		}

		return "login credentials success";

	}
	/*
	 * public List<UserEntity> getAllUsersWithUserRole() { return
	 * userRepo.findAllUsersByRoleId(); }
	 */

	public List<UserEntity> getAllUsersWithUserRole() {
		RoleEntity userRole = roleRepo.findByRoleName("USER");

		if (userRole == null) {
			return Collections.emptyList();
		}
		List<UserEntity> users = userRepo.findAll();
		if (users == null) {
			return Collections.emptyList();
		}
		List<UserEntity> usersWithUserRole = users.stream().filter(user -> user.getRole().equals(userRole))
				.collect(Collectors.toList());

		return usersWithUserRole;
	}

	public List<UserEntity> getAllUsersIsActive() {
		List<UserEntity> users = userRepo.findAll();

		if (users == null) {
			return Collections.emptyList();
		}

		List<UserEntity> activeUsers = users.stream().filter(user -> user.isActive()).collect(Collectors.toList());

		return activeUsers;
	}

	public List<UserEntity> getAllUsersIsInactive() {
		List<UserEntity> users = userRepo.findAll();

		if (users == null) {
			return Collections.emptyList();
		}

		List<UserEntity> inactiveUsers = users.stream().filter(user -> !user.isActive())

				.collect(Collectors.toList());

		return inactiveUsers;
	}
}
