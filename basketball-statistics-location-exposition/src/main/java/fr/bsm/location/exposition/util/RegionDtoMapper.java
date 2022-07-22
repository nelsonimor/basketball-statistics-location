package fr.bsm.location.exposition.util;

import org.mapstruct.Mapper;

import fr.bsm.location.domain.common.entity.region.RegionEntity;
import fr.bsm.location.domain.common.entity.region.RegionsEntity;
import fr.bsm.location.exposition.dto.RegionDto;
import fr.bsm.location.exposition.dto.RegionsDto;


@Mapper(componentModel = "spring", uses = {})
public interface RegionDtoMapper {

	RegionDto entityToDto(RegionEntity regionEntity);
	
	RegionsDto entityToRegionsDto(RegionsEntity regionsEntity);
	
}
