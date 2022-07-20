package fr.basketball.statistics.location.infrastructure.util;

import org.mapstruct.Mapper;

import fr.basketball.statistics.location.infrastructure.data.continent.ContinentData;
import fr.bsm.location.domain.common.entity.continent.ContinentEntity;

@Mapper(componentModel = "spring")
public interface ContinentEntityMapper {


  ContinentEntity dataToEntity(ContinentData continentData);

  ContinentData entityToData(ContinentEntity continentEntity);
}
