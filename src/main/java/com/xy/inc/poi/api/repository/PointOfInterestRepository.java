package com.xy.inc.poi.api.repository;

import java.util.List;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.xy.inc.poi.api.entities.PointOfInterest;

//@NamedQueries({
//	@NamedQuery(name = "PointOfInterestRepository.findNearest", 
//			query = "SELECT p FROM poi p WHERE (p.coord_x BETWEEN (:coord_x - :radius) AND (:coord_x + :radius)) AND (p.coord_y BETWEEN (:coord_y - :radius) AND (:coord_y + :radius)) ORDER BY p.name") })
public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Integer>  {
	
	@Transactional(readOnly = true)
	PointOfInterest findByName(String name);
	//List<PointOfInterest> findNearest(@Param("coord_x") Integer coord_x, @Param("coord_y") Integer coord_y, @Param("radius") Integer radius);	
}
