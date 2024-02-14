package com.zettamine.mi.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zettamine.mi.entities.Material;
import com.zettamine.mi.entities.MaterialCharacteristics;
import com.zettamine.mi.entities.Plant;

public interface MaterialRepository extends JpaRepository<Material, String> {

	    @Query("SELECT mc FROM MaterialCharacteristics mc JOIN mc.material m WHERE m.matrId = :id")
	    List<MaterialCharacteristics> materialCharacteristicsByMaterialId(@Param("id") String id);

		List<Material> findByStatus(String string);
				

}
