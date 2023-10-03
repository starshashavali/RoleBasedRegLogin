package com.org.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.domain.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	UserEntity findByUserEmail(String email);

	UserEntity findByUserEmailAndUserPwd(String username, String pwd);
	

/*	@Query("SELECT u FROM UserEntity u WHERE u.role = '2'")
	List<UserEntity> findAllUsersByRoleId();*/

}
