package com.zettamine.mi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zettamine.mi.entities.MaterialCharacteristics;
import com.zettamine.mi.repository.MaterialChRepository;

@Service
public class MaterialChServiceImpl implements MaterialChService {
	
	  MaterialChRepository chRepository;
	  
	  

	public MaterialChServiceImpl(MaterialChRepository chRepository) {
		super();
		this.chRepository = chRepository;
	}
 


	public void saveAll(List<MaterialCharacteristics> characteristicsList) {
		 
		chRepository.saveAll(characteristicsList);
		
		
	}


	@Override
	public MaterialCharacteristics findById(Integer matrChId) {
		
		return chRepository.findById(matrChId).get();
	}


	@Override
	public void save(MaterialCharacteristics matrCh) {
		
		  chRepository.save(matrCh);
	}
				
	  
	
}
