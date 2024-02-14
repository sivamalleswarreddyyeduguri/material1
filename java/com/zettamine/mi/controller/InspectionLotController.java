package com.zettamine.mi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zettamine.mi.entities.InspectionActuals;
import com.zettamine.mi.entities.InspectionLot;
import com.zettamine.mi.entities.Material;
import com.zettamine.mi.entities.Plant;
import com.zettamine.mi.entities.Vendor;
import com.zettamine.mi.model.LotSearchCriteria;
import com.zettamine.mi.service.InspectionLotService;
import com.zettamine.mi.service.MaterialServiceImpl;
import com.zettamine.mi.service.PlantService;
import com.zettamine.mi.service.VendorService;

@Controller
public class InspectionLotController {
	
	
	 private InspectionLotService inspectionLotService;
	 
	 private MaterialServiceImpl materialService;
	 
	 private VendorService vendorService;
	 
	 private PlantService plantService;
	 
	 

	public InspectionLotController(InspectionLotService inspectionLotService, MaterialServiceImpl materialService,
			VendorService vendorService, PlantService plantService) {
		super();
		this.inspectionLotService = inspectionLotService;
		this.materialService = materialService;
		this.vendorService = vendorService;
		this.plantService = plantService;
	}
	 

	

	  @GetMapping("/inspectionLotForm")
	  public String inspLotForm(Model model) {
	   model.addAttribute("inspLot", new InspectionLot());
	   
	     List<Material> material = materialService.findAll();
	     List<Vendor> vendor = vendorService.findAll();
	     List<Plant> plant = plantService.findAll();
	     
	    
	     
	     model.addAttribute("plants", plant);
	     model.addAttribute("vendors", vendor);
	     model.addAttribute("materials", material);

		 return "inspectionLot";
	  }
	 
	    
	    @PostMapping("/inspLotData")
	    public String addInspectionLot(InspectionLot inspLot, Model model) {
	    	
	    	
	    	
//	    	System.out.println(inspLot);
	        Material material = materialService.findById(inspLot.getMatrId().getMatrId());
	        Vendor vendor = vendorService.getVendorById(inspLot.getVendor().getVendorId());
	        Plant plant = plantService.getPlantById(inspLot.getPlantId().getPlantId());

	        inspLot.setMatrId(material);
	        inspLot.setVendor(vendor);
	        inspLot.setPlantId(plant);
	        inspectionLotService.save(inspLot);
	        model.addAttribute("inspLot", new InspectionLot());

	        return "redirect:/inspectionLots";
	    }
	    
	    @GetMapping("/inspLotSuccess")
	    public String inspLotSuccess(Model model) {
	    	  
	    	model.addAttribute("success", "Saved or updated Successfully!");
	    	return "redirect:/inspectionLotForm";
	    }
	    
	    @GetMapping("/inspectionLots")
	    public String viewInspectionLots(Model model) {
	    	    
	    	  List<InspectionLot> allInspectionLots = inspectionLotService.findAll();
	    	  
	    	  
	    	  model.addAttribute("materialService", materialService);
	    	  model.addAttribute("inspLots",allInspectionLots);
	    	  
	    	
	    	  
	    	  return "view-inspection-lots";
	    	   
	    }
	    
	    @GetMapping("/edit-lot/{lotId}")
	   public String editInspectionLot(@PathVariable ("lotId") Integer lotId, Model model) {
		   
	    	    InspectionLot inspObj = inspectionLotService.findById(lotId);
	    	
	    	    
	    	    model.addAttribute("updatedLot", inspObj);
	    	    
	    	    model.addAttribute("editMode", true);
	    	    
	    	    return "lot-update";
		              
	   }
	    
	    @PostMapping("/update-lot")
	    public String saveUpdatedLot(@RequestParam Integer lotId,
	                                 @RequestParam String result,
	                                 @RequestParam String remarks) {
	        InspectionLot existingLot = inspectionLotService.findById(lotId);
	        
	        existingLot.setResult(result);
	        existingLot.setRemarks(remarks);
	        
	        inspectionLotService.save(existingLot);
	       
	        return "redirect:/inspectionLots";
	    }
	    
	   
	     @GetMapping("/isplot/search")
	    public String findByLotId(@RequestParam Integer lotId, Model model) {
	    	 
	    	    try {
	    	     
	    	  InspectionLot inspObj = inspectionLotService.findById(lotId);
	    	  
	    	   model.addAttribute("inspLots",Arrays.asList(inspObj)); 
	    	   return "view-inspection-lots";
	        }  catch (Exception e) {
				 model.addAttribute("msg","please provide a valid lot Id");
				 return "view-inspection-lots";
			}
	    	    
	    }
	     
	      
	     @GetMapping("/search/results")
	     public String searchForm() {
	    	 
	    	 return "search-criteria";
	     }
	     
	     @PostMapping("/find/results")
	    public String findLotByCriteria(LotSearchCriteria searchCriteria, Model model) {
	    	 
	    	   System.err.println(searchCriteria);
	    	   
	    	 
	    	    List<InspectionLot> results = inspectionLotService.searchLotByCriteria(searchCriteria);
	    	    
	    	    model.addAttribute("inspLots", results);
	    	    
	    	    return "view-inspection-lots";
	     }
	     
	     
		 @GetMapping("select/lotid")
	     public String selectLotIdForActuals(Model model) {
	    	       
	    	 List<InspectionLot> all = inspectionLotService.findAll();
	    	       
	    	 model.addAttribute("inspLots", all);
	    	        
	    	   
	    	       return "add-inspection-actuals";
	     }
	   
	  

   }
