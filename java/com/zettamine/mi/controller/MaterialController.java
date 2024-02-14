package com.zettamine.mi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.zettamine.mi.entities.Material;
import com.zettamine.mi.entities.MaterialCharacteristics;
import com.zettamine.mi.entities.Plant;
import com.zettamine.mi.service.MaterialService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MaterialController {
		
	  MaterialService materialService;
	
	   
	 public MaterialController(MaterialService materialService) {
		super();
		this.materialService = materialService;
	}


	 @GetMapping("/materials")
	 public String materialForm(Model model, HttpSession session) {
	     model.addAttribute("material", new Material());
	     String successMessage = (String) session.getAttribute("success");
	     model.addAttribute("success", successMessage);
	     session.removeAttribute("success"); 
	     return "material";
	 }

	 @PostMapping("/materialData")
	 public String addMaterial(Material material, HttpSession session) {
	     materialService.save(material);
	     session.setAttribute("success", "Save or updated Successfully!!!!");
	     return "redirect:/materials";
	 }

	 @GetMapping("/viewMaterials")
	 public String viewMaterials(Material material, Model model) {
		 	
		    List<Material> materials = materialService.findByStatus();

		    model.addAttribute("material", materials);
		    
		    
		    return "material-view";
		   
	 }
	 
	   @GetMapping("/view-material/{matrId}")
	 	public String materialCharacteristics(@PathVariable ("matrId") String matrId, Model model) {
	 		  
		 List<MaterialCharacteristics> materialChar = materialService.materialCharacteristicsByMaterialId(matrId);    
	 		    
	 		    model.addAttribute("materialCharacter", materialChar);
	 		 
	 		    return "material-ch";
	 	}
	   
	   @GetMapping("/edit-material/{matrId}")
	 	public String materialUpdating(@PathVariable ("matrId") String matrId, Model model) {
	 		   Material material = materialService.findById(matrId);
	 		    
	 		    model.addAttribute("material", material);
	 		    model.addAttribute("editMode", true);
	 		    return "material";
	 	}
	   
	   @GetMapping("/delete-material/{matrId}")
	  public String softDeleting(@PathVariable ("matrId") String matrId, Model model) {
	 		 materialService.deleteById(matrId);
	 		 List<Material> updatedMaterials = materialService.findByStatus();
	 		 model.addAttribute("material", updatedMaterials);
	 		return "Material-view";
	 	}
	    
}
