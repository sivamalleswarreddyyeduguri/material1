package com.zettamine.mi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zettamine.mi.entities.Material;
import com.zettamine.mi.entities.MaterialCharacteristics;
import com.zettamine.mi.entities.Plant;
import com.zettamine.mi.entities.Vendor;
import com.zettamine.mi.service.MaterialChServiceImpl;
import com.zettamine.mi.service.MaterialService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MaterialChController {
		
	   
	   private MaterialService materialService;
	   
	   private MaterialChServiceImpl chServiceImpl;
	   
	 
	public MaterialChController(MaterialService materialService, MaterialChServiceImpl chServiceImpl) {
		super();
		this.materialService = materialService;
		this.chServiceImpl = chServiceImpl;
	}

	@GetMapping("/materialCh")
	public String materialForm(Model model, HttpSession session) {
//	    List<String> allMatrIds = materialService.getAllMatrIds();
		  List<Material> allMatrIds = materialService.findAll();
	    model.addAttribute("matrIds", allMatrIds);
	    String successMessage = (String) session.getAttribute("success");
	    model.addAttribute("success", successMessage);
	    session.removeAttribute("success"); 
	    model.addAttribute("materialCh", new MaterialCharacteristics());
	    return "material-select";
	}
	
	

	
	
	@PostMapping("/materialchform")
	public String processForm(@RequestParam String materialId, @RequestParam int numberOfCharacteristics, RedirectAttributes redirectAttributes) {
	    Material matrObj = materialService.findById(materialId);
	    redirectAttributes.addAttribute("numCharacteristics", numberOfCharacteristics);
	    redirectAttributes.addFlashAttribute("material", matrObj);
	    return "redirect:/matr-ch/{numCharacteristics}";
	}


	@GetMapping("/matr-ch/{numCharacteristics}")
	public String matrChForm(@PathVariable int numCharacteristics, @ModelAttribute("material") Material material, Model model) {
	    List<MaterialCharacteristics> characteristicsList = new ArrayList<>();
	    for (int i = 0; i < numCharacteristics; i++) {
	        characteristicsList.add(new MaterialCharacteristics());
	    }
	    material.setMatrCh(characteristicsList);
	    model.addAttribute("material", material);
	    model.addAttribute("characteristicsList", characteristicsList);
	    return "matr-ch";
	}


	
	@PostMapping("/saveMaterial")
	public String saveMaterial(@ModelAttribute("material") Material material, Model model) {
	    String materialId = material.getMatrId();
	    
	    Material existingMaterial = materialService.findById(materialId);

	    List<MaterialCharacteristics> characteristicsList = material.getMatrCh();

	    for (MaterialCharacteristics characteristic : characteristicsList) {
	        characteristic.setMaterial(existingMaterial);
	    }

	     chServiceImpl.saveAll(characteristicsList);
	     
	     return "material-search";
	}
	
	 
	@GetMapping("/edit-characteristics/{matrChId}")
 	public String materialCharacteristicsUpdating(@PathVariable ("matrChId") Integer matrChId, Model model) {
 		   MaterialCharacteristics matrChar = chServiceImpl.findById(matrChId);
 		    
 		    model.addAttribute("material", matrChar);
 		    model.addAttribute("editMode", true);
 		    return "material-ch-update";
 	}
	
	
//	  @GetMapping("/delete-materialCharacteristics/{matrChId}")
//	  public String softDeleting(@PathVariable ("matrChId") String matrChId, Model model) {
//	 		 chServiceImpl.deleteById(matrChId);
//	 		 List<> updatedMaterials = materialService.findByStatus();
//	 		 model.addAttribute("material", updatedMaterials);
//	 		return "Material-view";
//	 	}
	
	
	@PostMapping("/saveMaterialCharData")
	public String saveUpdatedMatrChar(MaterialCharacteristics matrCh, Model model) {
		  
		chServiceImpl.save(matrCh);
		model.addAttribute("success", "updated Successfully!!!!");
		model.addAttribute("material", new MaterialCharacteristics());
		return"material-search";
	}
	
	
                                 

}
	
	

