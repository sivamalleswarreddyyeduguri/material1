package com.zettamine.mi.service;

import java.util.List;

import com.zettamine.mi.entities.Plant;

public interface PlantService {

	void save(Plant plant);

	List<Plant> findByActions();
	
	boolean deleteById(String id);

	Plant getPlantById(String vendorId);

	List<Plant> findAll();
}
