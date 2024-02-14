package com.zettamine.mi.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zettamine.mi.entities.InspectionActuals;
import com.zettamine.mi.entities.InspectionLot;
import com.zettamine.mi.entities.MaterialCharacteristics;
import com.zettamine.mi.entities.User;
import com.zettamine.mi.model.LotSearchCriteria;
import com.zettamine.mi.repository.InspectionLotRepository;

@Service
public class inspectionLotServiceImpl implements InspectionLotService {
	
	InspectionLotRepository lotRepository;
		
	
	
	public inspectionLotServiceImpl(InspectionLotRepository lotRepository) {
		super();
		this.lotRepository = lotRepository;
	}



	@Override
	public void save(InspectionLot inspLot) {
		
		 lotRepository.save(inspLot);

	}
	
	@Override
	public List<InspectionLot> findAll(){
		
		    return lotRepository.findAll();
	}



	@Override
	public InspectionLot findById(Integer lotId) {
		
		return lotRepository.findById(lotId).get();
	}
	
	
	@Override
	public void measurementsValidations(InspectionLot lot, User user) {
		
		if(lot.getInspActuals().size() == lot.getMatrId().getMatrCh().size()) {

	    List<MaterialCharacteristics> matrCh = lot.getMatrId().getMatrCh();
	    List<InspectionActuals> inspActuals = lot.getInspActuals();

	    for (int i = 0; i < lot.getMatrId().getMatrCh().size(); i++) {
	        String matrDesc = matrCh.get(i).getMaterial().getMatrDesc();
	        String chDesc = matrCh.get(i).getChDesc();

	        if (inspActuals.get(i).getMaxMeasure() <= matrCh.get(i).getTolUl()
	                && inspActuals.get(i).getMinMeasure() >= matrCh.get(i).getTolLl()) {
	                     continue;
	        } 
	        else {
	            lot.setResult("Inspection completed");
	            lot.setRemarks(matrDesc + " " + chDesc + " measurements failed");
	            lot.setInspEndDate(LocalDate.now());
	            lot.setUserId(user);
	            lotRepository.save(lot);
	            return;
	        }
	    }

	    lot.setResult("PASS");
	    lot.setRemarks("no remarks");
	    lot.setInspEndDate(LocalDate.now());
	    lot.setUserId(user);
	    lotRepository.save(lot);
	}

	}


//	@Override
//	public List<InspectionLot> searchLotByCriteria(LotSearchCriteria criteria) {
//		
//        List<InspectionLot> criteriaList = lotRepository
//                .findAllByInspStDateBetween(criteria.getStartDate(), criteria.getEndDate())
//                .stream()
//                
//                .filter(lot -> {
//                      if (criteria.getMaterialNo().equals("")) 
//                           return true;
//                      return lot.getMatrId().getMatrId().equals(criteria.getMaterialNo());})
//                
//                .filter(lot -> {
//                      if (criteria.getPlantId().equals("")) 	
//                           return true;
//                           return lot.getPlantId().getPlantId().equals(criteria.getPlantId());})
//                
//                .filter(lot -> {
//                      if (criteria.getResult() != null) 
//                           return lot.getResult().equals(criteria.getResult());
//                      return true;})
//                .collect(Collectors.toList());
//
//		return criteriaList;
//	}
	
//	@Override
//	public List<InspectionLot> searchLotByCriteria(LotSearchCriteria criteria) {
//
//		List<InspectionLot> criteriaList;
//		
//		if ((criteria.getMaterialNo().isEmpty()) &&
//		    (criteria.getPlantId().isEmpty()) &&
//		    criteria.getResult() == null &&
//		    (criteria.getStartDate() == null || criteria.getEndDate() == null)) {
//		    criteriaList = lotRepository.findAll();
//		}
//		
//		
//		else {
//		    criteriaList = lotRepository
//		        .findAllByInspStDateBetween(criteria.getStartDate(), criteria.getEndDate())
//		        .stream()
//		        .filter(lot -> {
//		            if ( criteria.getMaterialNo().isEmpty()) 
//		                return true;
//		            return lot.getMatrId().getMatrId().equals(criteria.getMaterialNo());
//		        })
//		        .filter(lot -> {
//		            if (criteria.getPlantId().isEmpty()) 
//		                return true;
//		            return lot.getPlantId().getPlantId().equals(criteria.getPlantId());
//		        })
//		        .filter(lot -> {
//		            if (criteria.getResult() != null) 
//		                return lot.getResult().equals(criteria.getResult());
//		            return true;
//		        })
//		        .collect(Collectors.toList());
//		}
//
//		return criteriaList;
//
//	}
	
	
	
	@Override
	public List<InspectionLot> searchLotByCriteria(LotSearchCriteria criteria) {
	    List<InspectionLot> criteriaList;

	    LocalDate startDate = criteria.getStartDate();
	    LocalDate endDate = criteria.getEndDate();

	    if (startDate != null && endDate != null) {
	        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
	        if (daysBetween > 90) {
	            throw new IllegalArgumentException("End date must be within 90 days of the start date");
	        }
	    }

	    criteriaList = lotRepository
	            .findAllByCreatedOnBetween(criteria.getStartDate(), criteria.getEndDate())
	            .stream()
	            .filter(lot -> {
	                if (criteria.getMaterialNo().isEmpty()) {
	                    return true;
	                }
	                return lot.getMatrId().getMatrId().equals(criteria.getMaterialNo());
	            })
	            .filter(lot -> {
	                if (criteria.getPlantId().isEmpty()) {
	                    return true;
	                }
	                return lot.getPlantId().getPlantId().equals(criteria.getPlantId());
	            })
	            .filter(lot -> {
	                if (criteria.getResult() != null) {
	                
	                	return lot.getResult() != null && lot.getResult().equals(criteria.getResult());
	                }
	                return true;
	                
	            })
	            .collect(Collectors.toList());

	    return criteriaList;
	}

	
    

}
