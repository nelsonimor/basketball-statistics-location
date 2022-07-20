package fr.basketball.statistics.location.exposition.util;

import org.mapstruct.Mapper;

import fr.basketball.statistics.location.exposition.dto.ContinentDto;
import fr.basketball.statistics.location.exposition.dto.ContinentsDto;
import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.continent.ContinentsEntity;


@Mapper(componentModel = "spring", uses = {})
public interface ContinentDtoMapper {

	ContinentDto entityToDto(ContinentEntity continentEntity);
	
	ContinentsDto entityToContinentsDto(ContinentsEntity continentsEntity);
	
}
