package com.zettamine.mi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.mi.entities.MaterialCharacteristics;

public interface MaterialChRepository extends JpaRepository<MaterialCharacteristics, Integer> {

}
