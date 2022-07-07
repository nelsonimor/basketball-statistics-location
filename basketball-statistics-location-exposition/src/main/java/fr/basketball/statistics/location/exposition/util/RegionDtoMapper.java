package fr.basketball.statistics.location.exposition.util;

import org.mapstruct.Mapper;

import fr.basketball.statistics.location.domain.common.entity.region.RegionEntity;
import fr.basketball.statistics.location.domain.common.entity.region.RegionsEntity;
import fr.basketball.statistics.location.exposition.dto.RegionDto;
import fr.basketball.statistics.location.exposition.dto.RegionsDto;


@Mapper(componentModel = "spring", uses = {})
public interface RegionDtoMapper {

	RegionDto entityToDto(RegionEntity regionEntity);
	
	RegionsDto entityToRegionsDto(RegionsEntity regionsEntity);
	
}
