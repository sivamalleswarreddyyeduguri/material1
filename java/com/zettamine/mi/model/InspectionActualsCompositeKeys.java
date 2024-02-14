package com.zettamine.mi.model;

import java.io.Serializable; 
import java.util.Objects;

import com.zettamine.mi.entities.InspectionLot;
import com.zettamine.mi.entities.MaterialCharacteristics;

public class InspectionActualsCompositeKeys implements Serializable {
			
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MaterialCharacteristics characteristics;
	
	private InspectionLot inspLot;

	public InspectionActualsCompositeKeys(MaterialCharacteristics characteristics, InspectionLot inspLot) {
		super();
		this.characteristics = characteristics;
		this.inspLot = inspLot;
	}

	public InspectionActualsCompositeKeys() {
		super();
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(characteristics, inspLot);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InspectionActualsCompositeKeys other = (InspectionActualsCompositeKeys) obj;
		return Objects.equals(characteristics, other.characteristics) && Objects.equals(inspLot, other.inspLot);
	}
	
	
	
	
}
