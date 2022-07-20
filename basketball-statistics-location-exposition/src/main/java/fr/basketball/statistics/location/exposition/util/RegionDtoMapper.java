package fr.basketball.statistics.location.exposition.util;

import org.mapstruct.Mapper;

import fr.basketball.statistics.location.exposition.dto.RegionDto;
import fr.basketball.statistics.location.exposition.dto.RegionsDto;
import fr.bsm.location.domain.common.entity.region.RegionEntity;
import fr.bsm.location.domain.common.entity.region.RegionsEntity;


@Mapper(componentModel = "spring", uses = {})
public interface RegionDtoMapper {

	RegionDto entityToDto(RegionEntity regionEntity);
	
	RegionsDto entityToRegionsDto(RegionsEntity regionsEntity);
	
}
