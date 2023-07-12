package fr.bsm.location.exposition.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.exposition.dto.CitiesDto;
import fr.bsm.location.exposition.dto.CityDto;
import fr.bsm.location.exposition.dto.CityRequestDto;


@Mapper(componentModel = "spring", uses = {})
public interface CityDtoMapper {

	@Mapping(source = "country.codeiso2", target = "country.flagurl", qualifiedByName = "buildFlag")
	CityDto entityToDto(CityEntity cityEntity);
	
	
	CityEntity dtoToEntity(CityRequestDto cityRequestDto);
	
	CitiesDto entityToCitiesDto(CitiesEntity citiesEntity);
	
	@Named("buildFlag")
	public static String buildFlag(String codeiso2) {
		return "http://paulemignoni.alwaysdata.net/flags/16x16/"+codeiso2.toLowerCase()+".png";
	}
	
}
