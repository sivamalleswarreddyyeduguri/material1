package com.zettamine.mi.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MAT_ISP_CH")
public class MaterialCharacteristics {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "matrCh_generator")
	@SequenceGenerator(name = "matrCh_generator",sequenceName = "matrCh_sequence",initialValue = 101,allocationSize = 1)
	private Integer matrChId;
	private String chDesc;
	private Float tolUl;
	private Float tolLl;
	private String uom;
	
	@ManyToOne
	@JoinColumn(name =  "material_id")
	private Material material;
	
	
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "characteristics")
    private List<InspectionActuals> inspActuals;

}
