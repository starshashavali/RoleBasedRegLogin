package com.org.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Role_DTLS")
public class RoleEntity {
	@Id
	private Integer roleId;
	
	private String roleName;
	


}
