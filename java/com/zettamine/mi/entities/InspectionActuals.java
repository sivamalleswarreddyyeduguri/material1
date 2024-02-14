package com.zettamine.mi.entities;

import java.util.List;

import com.zettamine.mi.model.InspectionActualsCompositeKeys;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(InspectionActualsCompositeKeys.class)
public class InspectionActuals {
		
	 private float maxMeasure;
	 private float minMeasure;
	 
	 @Id
	 @ManyToOne
	 @JoinColumn(name = "lot_id")
	 private InspectionLot inspLot;
	 
	 @Id
	 @ManyToOne
	 @JoinColumn(name = "ch_id")
	 private MaterialCharacteristics characteristics;

	@Override
	public String toString() {
		return "InspectionActuals [maxMeasure=" + maxMeasure + ", minMeasure=" + minMeasure + ", inspLot=" + inspLot
				+ ", characteristics=" + characteristics + "]";
	}

	 
	  
}
