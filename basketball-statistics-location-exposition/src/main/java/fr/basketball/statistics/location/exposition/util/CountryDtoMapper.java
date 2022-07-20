package fr.basketball.statistics.location.exposition.util;

import org.mapstruct.Mapper;

import fr.basketball.statistics.location.exposition.dto.CountriesDto;
import fr.basketball.statistics.location.exposition.dto.CountryDto;
import fr.bsm.location.domain.common.entity.country.CountriesEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;


@Mapper(componentModel = "spring", uses = {})
public interface CountryDtoMapper {

	CountryDto entityToDto(CountryEntity countryEntity);
	
	CountriesDto entityToCountriesDto(CountriesEntity countriesEntity);
	
}
