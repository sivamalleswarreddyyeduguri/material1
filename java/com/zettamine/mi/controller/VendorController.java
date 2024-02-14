package com.zettamine.mi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zettamine.mi.entities.Vendor;
import com.zettamine.mi.service.VendorServiceImpl;

@Controller

public class VendorController {
	
	 VendorServiceImpl serviceImpl;
	 
	  public VendorController(VendorServiceImpl serviceImpl) {
		super();
		this.serviceImpl = serviceImpl;
	}

	 @GetMapping("/vendor")
	  public String vendorForm(Model model) {
		  model.addAttribute("vendor", new Vendor());

			return "vendor";
	  }
	
	
	@PostMapping("/data")
	public String addVendor(Vendor vendor, Model model) {
		
		try {
	        serviceImpl.save(vendor);
	        model.addAttribute("success", "Save or updated Successfully!!!!");
	        model.addAttribute("vendor", new Vendor());
	    } catch (Exception e) {
	        model.addAttribute("msg", "Vendor name already exists");
	        model.addAttribute("vendor", new Vendor());
	    }
	    return "vendor";
		
	}


	
	@GetMapping("/view")
	public String viewVendors(Model model) {
		
		  List<Vendor> vendor = serviceImpl.findByStatus();   
		  model.addAttribute("vendors",vendor);
		  return "view-vendor";
	}
	
	
	@GetMapping("/edit/{vendorId}")
	public String vendorUpdating(@PathVariable ("vendorId") Integer vendorId, Model model) {
		 Vendor vendor = serviceImpl.getVendorById(vendorId);
		    
		    model.addAttribute("vendor", vendor);
		    return "vendor";
	}

	
	@GetMapping("/delete/{vendorId}")
	public String softDeleting(@PathVariable ("vendorId") Integer id, Model model) {
		 serviceImpl.deleteById(id);
		 List<Vendor> updatedVendors = serviceImpl.findByStatus();
		 model.addAttribute("vendors", updatedVendors);
		 model.addAttribute("vendor", new Vendor());
		return "view-vendor";
	}
	
	

}
