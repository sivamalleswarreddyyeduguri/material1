package com.zettamine.mi.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.mi.entities.InspectionLot;

public interface InspectionLotRepository extends JpaRepository<InspectionLot, Integer> {

	Collection<InspectionLot> findAllByInspStDateBetween(LocalDate startDate, LocalDate endDate);
			
	List<InspectionLot> findAllByCreatedOnBetween(LocalDate fromDate,LocalDate toDate);
	
	
//	       List<InspectionLot> findByResult(String result);
}
