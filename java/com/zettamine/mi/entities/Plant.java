package com.zettamine.mi.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Plant {
	
	@Id
	private String plantId;
	private String  plantName;
	private String  state;
	private String  city;
	private String actions = "active";
	
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "plantId")
	private List<InspectionLot> inspLot;
}
