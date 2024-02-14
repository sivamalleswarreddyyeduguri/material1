package com.zettamine.mi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zettamine.mi.entities.InspectionActuals;
import com.zettamine.mi.entities.MaterialCharacteristics;
import com.zettamine.mi.model.InspectionActualsCompositeKeys;

public interface InspectionActualsRepository extends JpaRepository<InspectionActuals, InspectionActualsCompositeKeys> {
		
	
	@Query("SELECT mc FROM MaterialCharacteristics mc WHERE mc.material.matrId = :matrId AND NOT EXISTS (SELECT ia FROM InspectionActuals ia WHERE ia.inspLot.matrId.matrId = :matrId AND mc.matrChId = ia.characteristics.matrChId)")
    List<MaterialCharacteristics> getAll(String matrId);
	
	@Query("SELECT ia FROM InspectionActuals ia WHERE ia.inspLot.id = :lotId")
	List<InspectionActuals> findActualsByLotId(Integer lotId);

//	InspectionActuals findActualsById(Integer lotId);
	
	

   
      
}
