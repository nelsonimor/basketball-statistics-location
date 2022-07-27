package fr.bsm.location.infrastructure.util;

import org.mapstruct.Mapper;

import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.infrastructure.data.city.CityData;

@Mapper(componentModel = "spring")
public interface CityEntityMapper {


	CityEntity dataToEntity(CityData cityData);

	CityData entityToData(CityEntity cityEntity);
}
