package fr.basketball.statistics.location.infrastructure.util;

import org.mapstruct.Mapper;

import fr.basketball.statistics.location.domain.common.entity.ContinentEntity;
import fr.basketball.statistics.location.infrastructure.data.ContinentData;

@Mapper(componentModel = "spring")
public interface ContinentEntityMapper {


  ContinentEntity dataToEntity(ContinentData continentData);

  ContinentData entityToData(ContinentEntity continentEntity);
}