package com.xy.inc.poi.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xy.inc.poi.api.dtos.PointOfInterestDto;
import com.xy.inc.poi.api.entities.PointOfInterest;
import com.xy.inc.poi.api.response.Response;
import com.xy.inc.poi.api.services.PointOfInterestService;

@RestController
@RequestMapping("/api/poi")
@CrossOrigin(origins = "*")
public class PointOfInterestController {

	private static final Logger log = LoggerFactory.getLogger(PointOfInterestController.class);

	@Autowired
	private PointOfInterestService pointOfInterestService;

	public PointOfInterestController() {
	}

	/**
	 * Cadastra um novo ponto de interesse.
	 * 
	 * @param pointOfInterestDto
	 * @param result
	 * @return ResponseEntity<Response<PointOfInterestDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping(value = "/create")
	public ResponseEntity<Response<PointOfInterestDto>> cadastrar(
			@Valid @RequestBody PointOfInterestDto pointOfInterestDto, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Creating POI: {}", pointOfInterestDto.toString());
		Response<PointOfInterestDto> response = new Response<PointOfInterestDto>();

		validateExistingData(pointOfInterestDto, result);
		PointOfInterest pointOfInterest = this.convertDtoToPointOfInterest(pointOfInterestDto, result);

		if (result.hasErrors()) {
			log.error("Error validating data from POI: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.pointOfInterestService.save(pointOfInterest);

		response.setData(this.convertPointOfInterestDto(pointOfInterest));
		return ResponseEntity.ok(response);
	}

	/**
	 * Retorna a listagem com todos os POIS.
	 * 
	 * @return ResponseEntity<Response<PointOfInterestDto>>
	 */
	@GetMapping(value = "/findAll")
	public ResponseEntity<Response<List<PointOfInterestDto>>> findAll() {
		log.info("Searching all POIs");
		Response<List<PointOfInterestDto>> response = new Response<List<PointOfInterestDto>>();

		Optional<List<PointOfInterest>> pointOfInterests = this.pointOfInterestService.findAll();

		if (!pointOfInterests.isPresent()) {
			log.info("No POI found!");
			response.getErrors().add(" No point of interest found!");
			return ResponseEntity.badRequest().body(response);
		}
		List<PointOfInterestDto> pointOfInterestDto = new ArrayList<PointOfInterestDto>();

		for (PointOfInterest obj : pointOfInterests.get()) {
			pointOfInterestDto.add(convertPointOfInterestDto(obj));
		}
		response.setData(pointOfInterestDto);
		return ResponseEntity.ok(response);
	}

	/**
	 * Retorna a listagem com os POIS próximos.
	 * 
	 * @return ResponseEntity<Response<PointOfInterestDto>>
	 */
	@GetMapping(value = "/findNearest/{coord_x}/{coord_y}")
	public ResponseEntity<Response<List<PointOfInterestDto>>> findNearest(@PathVariable("coord_x") Integer coord_x,
			@PathVariable("coord_y") Integer coord_y) {
		log.info("Searching nearest POIs: Coord x {}, Coord y {}", coord_x, coord_y);
		Response<List<PointOfInterestDto>> response = new Response<List<PointOfInterestDto>>();

		Optional<List<PointOfInterest>> pointOfInterests = this.pointOfInterestService.findNearest(coord_x, coord_y,
				10);

		if (!pointOfInterests.isPresent()) {
			log.info("No POI found!");
			response.getErrors().add(" No point of interest found!");
			return ResponseEntity.badRequest().body(response);
		}
		List<PointOfInterestDto> pointOfInterestDto = new ArrayList<PointOfInterestDto>();

		for (PointOfInterest obj : pointOfInterests.get()) {
			pointOfInterestDto.add(convertPointOfInterestDto(obj));
		}
		response.setData(pointOfInterestDto);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Converte os dados do DTO para PointOfInterest.
	 * 
	 * @param pointOfInterestDto
	 * @return PointOfInterest
	 */
	private PointOfInterest convertDtoToPointOfInterest(@Valid PointOfInterestDto pointOfInterestDto,
			BindingResult result) throws NoSuchAlgorithmException {
		PointOfInterest pointOfInterest = new PointOfInterest();
		pointOfInterest.setName(pointOfInterestDto.getName());
		pointOfInterest.setId(pointOfInterestDto.getId());
		pointOfInterest.setCoord_x(pointOfInterestDto.getCoord_x());
		pointOfInterest.setCoord_y(pointOfInterestDto.getCoord_y());
		return pointOfInterest;
	}

	/**
	 * Verifica se o point of interest é valido.
	 * 
	 * @param pointOfInterestDto
	 * @param result
	 */
	private void validateExistingData(@Valid PointOfInterestDto pointOfInterestDto, BindingResult result) {
		if (pointOfInterestDto.getName().isEmpty()) {
			result.rejectValue("Name", "Name can't be empty.");
		}
		if (pointOfInterestDto.getCoord_x() < 0) {
			result.rejectValue("Coordenate X", "The value must be positive.");
		}
		if (pointOfInterestDto.getCoord_y() < 0) {
			result.rejectValue("Coordenate Y", "The value must be positive.");
		}
	}
	

	/**
	 * Popula um DTO com os dados de uma POI.
	 * 
	 * @param pointOfInterest
	 * @return pointOfInterestDto
	 */
	private PointOfInterestDto convertPointOfInterestDto(PointOfInterest pointOfInterest) {

		PointOfInterestDto pointOfInterestDto = new PointOfInterestDto();
		pointOfInterestDto.setId(pointOfInterest.getId());
		pointOfInterestDto.setName(pointOfInterest.getName());
		pointOfInterestDto.setCoord_x(pointOfInterest.getCoord_x());
		pointOfInterestDto.setCoord_y(pointOfInterest.getCoord_y());
		return pointOfInterestDto;
	}
}
