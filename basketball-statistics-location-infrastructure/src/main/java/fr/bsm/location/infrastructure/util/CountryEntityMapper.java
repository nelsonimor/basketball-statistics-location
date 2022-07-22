package fr.bsm.location.infrastructure.util;

import org.mapstruct.Mapper;

import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.infrastructure.data.country.CountryData;

@Mapper(componentModel = "spring")
public interface CountryEntityMapper {


	CountryEntity dataToEntity(CountryData countryData);

	CountryData entityToData(CountryEntity countryEntity);
}
