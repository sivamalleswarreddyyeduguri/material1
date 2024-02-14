package com.zettamine.mi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.zettamine.mi.entities.Plant;
import com.zettamine.mi.entities.Vendor;
import com.zettamine.mi.service.PlantService;

@Controller
public class PlantController {
	 
	PlantService plantService;
	
	  public PlantController(PlantService plantService) {
		  
		this.plantService = plantService;
	}

	  @GetMapping("/plant")
	  public String plantForm(Model model) {
	   model.addAttribute("plant", new Plant());
	   model.addAttribute("editMode", false);
			return "plant";
	  }
	 
	    @PostMapping("/plantData")
		public String addPlant(Plant plant, Model model) {
			
	    	plantService.save(plant);
	    	model.addAttribute("success", "Save or updated Successfully!!!!");
			model.addAttribute("plant", new Plant());
			 
			return "plant";
		}
	     
	     @GetMapping("/viewData")
	 	public String viewPlants(Model model) {
	 		
	 		  List<Plant> plant = plantService.findByActions();   
	 		  model.addAttribute("plants",plant);
	 		  return "view-plants";
	 	}
	     
	     
	     @GetMapping("/edit-plant/{plantId}")
	 	public String PlantUpdating(@PathVariable ("plantId") String plantId, Model model) {
	 		   Plant plant = plantService.getPlantById(plantId);
	 		    
	 		    model.addAttribute("plant", plant);
	 		    model.addAttribute("editMode", true);
	 		    return "plant";
	 	}
	     
	    
	     @GetMapping("/delete-plant/{plantId}")
	 	public String softDeleting(@PathVariable ("plantId") String id, Model model) {
	 		 plantService.deleteById(id);
	 		 List<Plant> updatedPlants = plantService.findByActions();
	 		 model.addAttribute("plants", updatedPlants);
	 		 model.addAttribute("plant", new Plant());
	 		return "view-plants";
	 	}
	     
	     
	     
	
}
