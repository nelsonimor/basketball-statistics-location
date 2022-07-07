package fr.basketball.statistics.location.exposition.util;

import org.mapstruct.Mapper;

import fr.basketball.statistics.location.domain.common.entity.continent.ContinentEntity;
import fr.basketball.statistics.location.domain.common.entity.continent.ContinentsEntity;
import fr.basketball.statistics.location.exposition.dto.ContinentDto;
import fr.basketball.statistics.location.exposition.dto.ContinentsDto;


@Mapper(componentModel = "spring", uses = {})
public interface ContinentDtoMapper {

	ContinentDto entityToDto(ContinentEntity continentEntity);
	
	ContinentsDto entityToContinentsDto(ContinentsEntity continentsEntity);
	
}
