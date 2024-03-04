package com.slokam.scriptone.service;

import java.util.List;

import com.slokam.scriptone.dto.LocationDTO;

public interface IlocationService {

	abstract public LocationDTO savingdata(LocationDTO loction);

	abstract public LocationDTO GettingDatabyId(Long locationdid);
	
	abstract public LocationDTO DeletingDatabyid(Long locationid);
	
	abstract public	List<LocationDTO> Gettingalldata();
	
}
