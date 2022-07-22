package fr.bsm.location.exposition.util;

import org.mapstruct.Mapper;

import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.continent.ContinentsEntity;
import fr.bsm.location.exposition.dto.ContinentDto;
import fr.bsm.location.exposition.dto.ContinentsDto;


@Mapper(componentModel = "spring", uses = {})
public interface ContinentDtoMapper {

	ContinentDto entityToDto(ContinentEntity continentEntity);
	
	ContinentsDto entityToContinentsDto(ContinentsEntity continentsEntity);
	
}
