package fr.bsm.location.exposition.util;

import org.mapstruct.Mapper;

import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.exposition.dto.CitiesDto;
import fr.bsm.location.exposition.dto.CityDto;


@Mapper(componentModel = "spring", uses = {})
public interface CityDtoMapper {

	CityDto entityToDto(CityEntity cityEntity);
	
	CitiesDto entityToCitiesDto(CitiesEntity citiesEntity);
	
}