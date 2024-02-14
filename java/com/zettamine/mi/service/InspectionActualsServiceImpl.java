package com.zettamine.mi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zettamine.mi.entities.InspectionActuals;
import com.zettamine.mi.entities.MaterialCharacteristics;
import com.zettamine.mi.repository.InspectionActualsRepository;

@Service
public class InspectionActualsServiceImpl implements InspectionActualsService{
			
	   
	 private InspectionActualsRepository actualsRepository;

	public InspectionActualsServiceImpl(InspectionActualsRepository actualsRepository) {
		super();
		this.actualsRepository = actualsRepository;
	}
	  
	@Override
	public void save(InspectionActuals actuals) {
	       
//		 actualsRepository.save(actuals);
		   actuals.getInspLot().setResult("Processing");
		   actualsRepository.save(actuals);
	}

	@Override
	public List<InspectionActuals> findAll() {
		
		
		return actualsRepository.findAll();
	}

	@Override
	public List<MaterialCharacteristics> getAll(String matrId) {
		
		return actualsRepository.getAll(matrId);
	}

	  @Override
	public List<InspectionActuals> findActualsByLotId(Integer lotId) {
		
		return actualsRepository.findActualsByLotId(lotId);
		
	}
	  

	  
	  
	  
	  

//    @Override
//	public InspectionActuals findActualsById(Integer lotId) {
//		actualsRepository.findb
//		return actualsRepository.findActualsById(lotId);
//	}
}
