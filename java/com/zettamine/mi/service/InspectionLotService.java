package com.zettamine.mi.service;

import java.util.List;

import com.zettamine.mi.entities.InspectionLot;
import com.zettamine.mi.entities.User;
import com.zettamine.mi.model.LotSearchCriteria;

public interface InspectionLotService {
			
	   void save(InspectionLot inspLot);

	   List<InspectionLot> findAll();
	   
	   InspectionLot findById(Integer lotId);
	   
	   void measurementsValidations(InspectionLot lot,User user);
	   
	   List<InspectionLot> searchLotByCriteria(LotSearchCriteria criteria);
	   
	
}
