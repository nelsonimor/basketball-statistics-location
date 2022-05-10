package fr.basketball.statistics.location.infrastructure.util;

import org.mapstruct.Mapper;

import fr.basketball.statistics.location.domain.common.entity.RegionEntity;
import fr.basketball.statistics.location.infrastructure.data.RegionData;

@Mapper(componentModel = "spring")
public interface RegionsEntityMapper {


  RegionEntity dataToEntity(RegionData regionData);

  RegionData entityToData(RegionEntity regionEntity);
}
