package com.zettamine.mi.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InspectionLot {
		
	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "inspLot_generator")
	  @SequenceGenerator(name = "inspLot_generator",sequenceName = "inspLot_sequence",initialValue = 4801,allocationSize = 1)
	  private Integer lotId;
	  private LocalDate createdOn;
	  private LocalDate inspStDate;
	  private LocalDate inspEndDate;
	  private String result;
	  private String remarks;
	
	  @ManyToOne
	  @JoinColumn(name = "matr_id")
	  private Material matrId;
	   
	  @ManyToOne
//	  @JoinColumn(name = "vendor_id")
	  private Vendor vendor;


	  
	  @ManyToOne
	  @JoinColumn(name = "plant_id")
	  private Plant plantId;
	  
	  @ManyToOne
	  @JoinColumn(name = "user_id")
	  private User userId;
	  
	  
	  @OneToMany(cascade = CascadeType.ALL, mappedBy = "inspLot")
	  private List<InspectionActuals> inspActuals;  
	  
}
