package com.zettamine.mi.service;


import java.util.List;

import com.zettamine.mi.entities.Vendor;

public interface VendorService {
         
	  boolean save(Vendor vendor);
	  List<Vendor> findAll();
	  Vendor getVendorById(Integer id);
	   List<Vendor> findByStatus();

	
}
