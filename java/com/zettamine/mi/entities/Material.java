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
public class Material {
		
	   @Id
	   private String matrId;
	   private String matrDesc;
	   private String matrType;
	   private String status="active";
	   
	   @OneToMany(cascade = CascadeType.ALL, mappedBy = "matrId")
	   private List<InspectionLot> inspLot;
	   
	   @OneToMany(cascade = CascadeType.ALL,mappedBy = "material")
	   private List<MaterialCharacteristics> matrCh;
	   
	   
	 
	   
}
