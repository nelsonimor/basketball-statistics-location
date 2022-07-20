package fr.basketball.statistics.location.infrastructure.util;

import org.mapstruct.Mapper;

import fr.basketball.statistics.location.infrastructure.data.region.RegionData;
import fr.bsm.location.domain.common.entity.region.RegionEntity;

@Mapper(componentModel = "spring")
public interface RegionEntityMapper {


  RegionEntity dataToEntity(RegionData regionData);

  RegionData entityToData(RegionEntity regionEntity);
}
