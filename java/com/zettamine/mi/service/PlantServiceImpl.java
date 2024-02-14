package com.zettamine.mi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zettamine.mi.entities.Plant;
import com.zettamine.mi.entities.Vendor;
import com.zettamine.mi.repository.PlantRepository;

@Service
public class PlantServiceImpl implements PlantService {
	
	PlantRepository repository;

	public PlantServiceImpl(PlantRepository repository) {
		super();
		this.repository = repository;
	}



	@Override
	public void save(Plant plant) {
		
		   repository.save(plant);
		
	}


	@Override
	public List<Plant> findByActions() {
		
	   return repository.findByActions("active");

	}
	
	@Override
	public boolean deleteById(String id) {
		
		 Optional<Plant> plant = repository.findById(id);
		if(plant.isPresent()) {
		     Plant softDel = plant.get();
			 softDel.setActions("in-active");
			repository.save(softDel);
			return true;
		}
	return false;
}



	@Override
	public Plant getPlantById(String vendorId) {
		
		Optional<Plant> findById = repository.findById(vendorId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}



	@Override
	public List<Plant> findAll() {
		
		return repository.findAll();
	}
  }

