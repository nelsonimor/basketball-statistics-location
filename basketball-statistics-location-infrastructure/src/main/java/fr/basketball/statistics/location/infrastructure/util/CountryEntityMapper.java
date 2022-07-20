package fr.basketball.statistics.location.infrastructure.util;

import org.mapstruct.Mapper;

import fr.basketball.statistics.location.domain.common.entity.country.CountryEntity;
import fr.basketball.statistics.location.infrastructure.data.country.CountryData;

@Mapper(componentModel = "spring")
public interface CountryEntityMapper {


	CountryEntity dataToEntity(CountryData countryData);

	CountryData entityToData(CountryEntity countryEntity);
}
