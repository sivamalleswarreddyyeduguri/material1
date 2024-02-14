package com.zettamine.mi.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.mi.entities.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {

	List<Vendor> findByStatus(String string);
			
	
	      
	  
}
