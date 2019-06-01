package com.xy.inc.poi.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xy.inc.poi.api.entities.PointOfInterest;
import com.xy.inc.poi.api.repository.PointOfInterestRepository;
import com.xy.inc.poi.api.services.PointOfInterestService;

@Service
public class PointOfInterestServiceImpl implements PointOfInterestService {
	
	private static final Logger log = LoggerFactory.getLogger(PointOfInterestServiceImpl.class);
	
	@Autowired
	private PointOfInterestRepository pointOfInterestRepository;

	@Override
	public Optional<List<PointOfInterest>> findAll() {
		log.info("Find All POIs {}", "");
		return Optional.ofNullable(pointOfInterestRepository.findAll());
	}

	private Predicate<PointOfInterest> isNearest(Integer coord_x, Integer coord_y, Integer radius) {
        return p -> Math.sqrt(Math.pow(p.getCoord_x() - coord_x, 2) - Math.pow(p.getCoord_y() - coord_y, 2)) < radius;
	}
	
	private List<PointOfInterest> filterPOI (List<PointOfInterest> pointOfInterests, Predicate<PointOfInterest> predicate) 
    {
        return pointOfInterests.stream()
                    .filter(predicate)
                    .collect(Collectors.<PointOfInterest>toList());
    }
	
	@Override
	public Optional<List<PointOfInterest>> findNearest(Integer coord_x, Integer coord_y, Integer radius) {
		log.info("Find Nearest POIs {}", "Coord_x " + coord_x + ", Coord_y " + coord_y + ", Radius " + radius);
		return Optional.ofNullable(filterPOI(pointOfInterestRepository.findAll(), isNearest(coord_x, coord_y, radius)));
	}

	@Override
	public PointOfInterest save(PointOfInterest pointOfInterest) {
		log.info("Saving POI: {}", pointOfInterest);
		return this.pointOfInterestRepository.save(pointOfInterest);
	}

}
