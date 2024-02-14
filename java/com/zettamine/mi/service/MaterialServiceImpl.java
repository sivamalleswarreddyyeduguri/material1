package com.zettamine.mi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zettamine.mi.entities.Material;
import com.zettamine.mi.entities.MaterialCharacteristics;
import com.zettamine.mi.entities.Plant;
import com.zettamine.mi.repository.MaterialRepository;

@Service
public class MaterialServiceImpl implements MaterialService {
	
	MaterialRepository repository;
	
	public MaterialServiceImpl(MaterialRepository repository) {
		super();
		this.repository = repository;
	}


	@Override
	public void save(Material material) {
	      
		  repository.save(material);
		
	}
	
	@Override
	public List<String> getAllMatrIds(){
		
		   List<Material> mtr = repository.findAll();
		   
		   List<String> ids = new ArrayList<>();
		   
		   for(Material matrs : mtr) {
			   ids.add(matrs.getMatrId());
		   }
		   
		   return ids;
	}


	@Override
	public Material findById(String id) {
	    
		  return repository.findById(id).get();
		
	}


	@Override
	public List<Material> findAll() {
		
		return repository.findAll();
	}


	@Override
	public List<MaterialCharacteristics> materialCharacteristicsByMaterialId(String Id) {
		
		return repository.materialCharacteristicsByMaterialId(Id);
	}


	@Override
	public boolean deleteById(String matrId) {
		
		Optional<Material> material = repository.findById(matrId);
		if(material.isPresent()) {
		     Material softDel = material.get();
			 softDel.setStatus("in-active");
			repository.save(softDel);
			return true;
		}
	return false;
		
	}


	@Override
	public List<Material> findByStatus() {
		
		return repository.findByStatus("active");
	}

	  

			
}
