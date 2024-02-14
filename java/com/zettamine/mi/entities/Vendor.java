package com.zettamine.mi.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "vendor_generator")
	  @SequenceGenerator(name = "vendor_generator",sequenceName = "vendor_sequence",initialValue = 5001,allocationSize = 1)
	  private Integer vendorId;
	  @Column(unique = true)
	  private String vendorName;
	  private String phoneNumber;
	  private String state;
	  private String city;
	  private String email;
	  private String status = "active";
	  
	  @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendor")
	  private List<InspectionLot> inspLot;

	  
	  
	 
}
