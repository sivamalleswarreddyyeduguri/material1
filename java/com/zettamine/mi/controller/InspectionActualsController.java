package com.zettamine.mi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.zettamine.mi.entities.InspectionActuals;
import com.zettamine.mi.entities.InspectionLot;
import com.zettamine.mi.entities.MaterialCharacteristics;
import com.zettamine.mi.entities.Plant;
import com.zettamine.mi.entities.User;
import com.zettamine.mi.service.InspectionActualsServiceImpl;
import com.zettamine.mi.service.InspectionLotService;
import com.zettamine.mi.service.MaterialChService;

@Controller
public class InspectionActualsController {
			
	   private InspectionLotService inspectionLotService;
	   
	   private InspectionActualsServiceImpl actualsService;
	
	   
	   private MaterialChService characteristics;

	public InspectionActualsController(@Autowired InspectionLotService inspectionLotService,
			@Autowired InspectionActualsServiceImpl actualsService,
			@Autowired MaterialChService characteristics ) {
		super();
		this.inspectionLotService = inspectionLotService;
		this.actualsService = actualsService;
		this.characteristics = characteristics;
	}
	
	
	@GetMapping("/addActual/{lotId}")
	public String AddActualsForm(@PathVariable("lotId") Integer lotId, Model model) {
		model.addAttribute("inspLotActuals", new InspectionActuals());
	    InspectionLot inspLots = inspectionLotService.findById(lotId);
	   // List<MaterialCharacteristics> materialCharacteristics = materialServiceImpl.materialCharacteristicsByMaterialId(inspLots.getMatrId().getMatrId());
	    
       List<MaterialCharacteristics> materialCharacteristics = actualsService.getAll(inspLots.getMatrId().getMatrId());
	    
	    
	    model.addAttribute("insplots", inspLots);
	    model.addAttribute("matrCharacteristics", materialCharacteristics);
	    return "actuals";
	}

	  
	  @PostMapping("/inspActualData")
	 public String saveActuals(InspectionActuals actuals, Model model,@SessionAttribute("user")User user) {
		
	     System.out.println(actuals.getCharacteristics().getMatrChId());
		 
		  InspectionLot inspLotObj = inspectionLotService.findById(actuals.getInspLot().getLotId());
		  
		   MaterialCharacteristics materialChObj = characteristics.findById(actuals.getCharacteristics().getMatrChId());
		   
		   actuals.setCharacteristics(materialChObj);
		   actuals.setInspLot(inspLotObj);
		   
		    actualsService.save(actuals);
		   
		    inspectionLotService.measurementsValidations(inspLotObj,user);
		   
		   
		    return "redirect:/view-actuals/" + inspLotObj.getLotId();
		 
	 }
	  
	 
	 @GetMapping("/view-actuals/{lotId}")
    public String viewActuals(@PathVariable ("lotId") Integer lotId, Model model) {
    	     
	    List<InspectionActuals> actuals = actualsService.findActualsByLotId(lotId);
		     
	      model.addAttribute("actuals",actuals);
 	  
 	      return "view-actuals";      
    }
	 
	 
	 
	 
	 
	 
//	   @GetMapping("/edit-actuals/{lotId}")
//	 	public String PlantUpdating(@PathVariable ("plantId") Integer lotId, Model model) {
//	 		   InspectionActuals actuals = actualsService.;
//	 		    
//	 		    model.addAttribute("actuals", actuals);
//	 		    return "actuals";
//	 	}

		  
	    
	
}
