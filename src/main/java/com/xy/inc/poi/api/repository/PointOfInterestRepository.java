package com.xy.inc.poi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.xy.inc.poi.api.entities.PointOfInterest;

public interface PointOfInterestRepository  extends JpaRepository<PointOfInterest, Long>  {
	
	@Transactional(readOnly = true)
	List<PointOfInterest> findAll();

	@Transactional(readOnly = true)
	List<PointOfInterest> findNearest(PointOfInterest point);
	
}
