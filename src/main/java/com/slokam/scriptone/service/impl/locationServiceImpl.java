package com.slokam.scriptone.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.slokam.scriptone.dao.ILocationDAO;
import com.slokam.scriptone.dao.IlocationTypeDao;
import com.slokam.scriptone.dto.LocationDTO;
import com.slokam.scriptone.dto.LocationTypeDto;
import com.slokam.scriptone.entity.Location;
import com.slokam.scriptone.entity.LocationType;
import com.slokam.scriptone.service.IlocationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class locationServiceImpl implements IlocationService{

	@Autowired
	private ILocationDAO locationdao;
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private IlocationTypeDao locationTypeDao;

	@Override
	public LocationDTO savingdata(LocationDTO locationDto) {


		log.trace(" Entering an location details ");
		
		Location  location	=	mapper.map(locationDto, Location.class);

		if(location.getLocationType().getId() != null)
		{
			log.info(locationDto.toString());
			
			Optional<LocationType> optionalLocationType = 
					locationTypeDao.findById(location.getLocationType().getId());

			if(optionalLocationType.isPresent())
			{
				location.setLocationType(optionalLocationType.get());
			}
		}


		locationdao.save(location);

		
		log.trace("Existing an location details");

		return locationDto;
	}

	@Override
	public LocationDTO GettingDatabyId(Long locationid) {

		log.trace("Entering an location id");

		Optional<Location>		location= locationdao.findById(locationid);
		if(location.isPresent()) {
			Location	locationentity=	location.get();
		}

		LocationDTO locationdto	=mapper.map(location, LocationDTO.class);

		log.trace("Existing an location details");	
		return locationdto;
	}

	@Override
	public LocationDTO DeletingDatabyid(Long locationid) {

		log.trace("Entering an location id");
		LocationDTO locationdto= GettingDatabyId(locationid);	
		this.locationdao.deleteById(locationid);
		log.trace("Existing an location details");
		return locationdto;
	}

	@Override
	public List<LocationDTO> Gettingalldata() {

		List<LocationDTO> locationdto=null;
		log.trace("Entering an location");
		try {
			List<Location>	locationentity	=this.locationdao.findAll();
		
				
			LocationDTO[]	loca=	mapper.map(locationentity, LocationDTO[].class);

			locationdto=Arrays.asList(loca);

		}catch(Exception exe){
			System.out.println("Data base Interaction Exception");
		}
		log.trace("Existing an location details");
		return locationdto;
	}


}
