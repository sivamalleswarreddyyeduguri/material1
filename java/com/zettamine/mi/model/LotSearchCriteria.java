package com.zettamine.mi.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class LotSearchCriteria {
			
	private LocalDate startDate;
    private LocalDate endDate;
    private String materialNo;
    private String plantId;
    private String result;
      
}
