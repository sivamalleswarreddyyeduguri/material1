package com.zettamine.mi.service;

import com.zettamine.mi.entities.MaterialCharacteristics;

public interface MaterialChService {

	MaterialCharacteristics findById(Integer matrChId);

	void save(MaterialCharacteristics matrCh);

}
