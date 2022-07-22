package fr.bsm.location.infrastructure.util;

import org.mapstruct.Mapper;

import fr.bsm.location.domain.common.entity.region.RegionEntity;
import fr.bsm.location.infrastructure.data.region.RegionData;

@Mapper(componentModel = "spring")
public interface RegionEntityMapper {


  RegionEntity dataToEntity(RegionData regionData);

  RegionData entityToData(RegionEntity regionEntity);
}
