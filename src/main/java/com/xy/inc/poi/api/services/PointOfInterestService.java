package com.xy.inc.poi.api.services;

import java.util.List;
import java.util.Optional;

import com.xy.inc.poi.api.entities.PointOfInterest;

public interface PointOfInterestService {
	/**
	 * Retorna todos os pontos de interesse
	 * 
	 * @return Optional<List<PointOfInterest>>
	 */
	Optional<List<PointOfInterest>> findAll();
	/**
	 * Retorna os pontos de interesse dentro do raio definido
	 * 
	 * @param coord_x
	 * @param coord_y
	 * @param radius
	 * @return Optional<List<PointOfInterest>>
	 */
	Optional<List<PointOfInterest>> findNearest(Integer coord_x, Integer coord_y, Integer radius);
	/**
	 * Grava um novo ponto de interesse
	 * 
	 * @param pointOfInterest
	 */
	PointOfInterest save(PointOfInterest pointOfInterest);
}
