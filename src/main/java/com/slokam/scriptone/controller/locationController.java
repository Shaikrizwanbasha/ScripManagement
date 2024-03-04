package com.slokam.scriptone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slokam.scriptone.dto.LocationDTO;
import com.slokam.scriptone.service.IlocationService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("locations")
public class locationController {
	@Autowired	
	private IlocationService locationservice;
	
	@PostMapping()	
	public ResponseEntity<LocationDTO> saveingData(@RequestBody	LocationDTO locationdto) {
		log.trace("Entering an data in an database");
				
		locationservice.savingdata(locationdto); 
//		log.debug(toString());
	log.trace("Existing data in an database");

		return ResponseEntity.ok(locationdto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<LocationDTO>gettingdata(@PathVariable("id") Long id){
		log.trace("Entering location details  ");

		LocationDTO   locationdto=	this.locationservice.GettingDatabyId(id);

		log.trace("Existing location details");
		return ResponseEntity.status(HttpStatus.OK).body(locationdto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<LocationDTO>Deletingdata(@PathVariable("id") Long id){
		log.trace("Entering location details  ");
		LocationDTO dto=	this.locationservice.DeletingDatabyid(id);
		log.trace("Existing location details");
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@GetMapping("/{all}")
	public ResponseEntity<List<LocationDTO>>Gettingalldata(){
		log.trace("Entering location details");

		List<LocationDTO>location=	this.locationservice.Gettingalldata();

		log.trace("Existing location details");
		return ResponseEntity.status(HttpStatus.OK).body(location);
	}

}
