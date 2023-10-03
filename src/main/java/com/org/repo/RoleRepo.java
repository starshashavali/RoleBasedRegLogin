package com.org.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.domain.RoleEntity;

public interface RoleRepo extends JpaRepository<RoleEntity, Integer> {
	
	RoleEntity findByRoleName(String role);
	

}
