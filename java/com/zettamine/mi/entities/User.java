package com.zettamine.mi.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
	
	@Id
	private Integer userId;
	private String userPassword;
	private String userName;
	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "")
//	InspectionLot inspLot;
	
}
