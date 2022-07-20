package fr.basketball.statistics.location.infrastructure.util;

import org.mapstruct.Mapper;

import fr.basketball.statistics.location.infrastructure.data.country.CountryData;
import fr.bsm.location.domain.common.entity.country.CountryEntity;

@Mapper(componentModel = "spring")
public interface CountryEntityMapper {


	CountryEntity dataToEntity(CountryData countryData);

	CountryData entityToData(CountryEntity countryEntity);
}
