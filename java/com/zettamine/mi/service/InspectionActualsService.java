package com.zettamine.mi.service;

import java.util.List;

import com.zettamine.mi.entities.InspectionActuals;
import com.zettamine.mi.entities.MaterialCharacteristics;

public interface InspectionActualsService {
		
	 void save(InspectionActuals actuals);
	 
	 List<InspectionActuals> findAll();
	 
	 List<MaterialCharacteristics> getAll(String matrId);

	List<InspectionActuals> findActualsByLotId(Integer lotId);

//	InspectionActuals findActualsById(Integer lotId);

	
}
