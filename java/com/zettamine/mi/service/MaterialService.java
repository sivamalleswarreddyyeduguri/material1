package com.zettamine.mi.service;

import java.util.List;

import com.zettamine.mi.entities.Material;
import com.zettamine.mi.entities.MaterialCharacteristics;
import com.zettamine.mi.entities.Plant;

public interface MaterialService {

	void save(Material material);

	List<String> getAllMatrIds();

	Material findById(String id);
	
	List<Material> findAll();

	List<MaterialCharacteristics> materialCharacteristicsByMaterialId(String id);

	boolean deleteById(String matrId);

	List<Material> findByStatus();
	
}
