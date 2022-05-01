package fr.basketball.statistics.location.exposition.util;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import fr.basketball.statistics.location.domain.common.entity.RegionEntity;
import fr.basketball.statistics.location.domain.common.entity.RegionsEntity;
import fr.basketball.statistics.location.exposition.dto.RegionDto;
import fr.basketball.statistics.location.exposition.dto.RegionsDto;


@Mapper(componentModel = "spring", uses = {})
public interface RegionDtoMapper {

	RegionDto entityToDto(RegionEntity regionEntity);
	
	RegionsDto entityToRegionsDto(RegionsEntity regionsEntity);
	
	default RegionsDto entitiesToDto(List<RegionEntity> entities) {
      return RegionsDto.builder()
          .items(entities.stream().map(this::entityToDto).collect(Collectors.toList()))
          .build();
	}
	

}
