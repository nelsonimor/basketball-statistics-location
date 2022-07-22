package fr.bsm.location.infrastructure.util;

import org.mapstruct.Mapper;

import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.infrastructure.data.continent.ContinentData;

@Mapper(componentModel = "spring")
public interface ContinentEntityMapper {


  ContinentEntity dataToEntity(ContinentData continentData);

  ContinentData entityToData(ContinentEntity continentEntity);
}
