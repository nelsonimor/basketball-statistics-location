package fr.bsm.location.exposition.util;

import org.mapstruct.Mapper;

import fr.bsm.location.domain.common.entity.country.CountriesEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.exposition.dto.CountriesDto;
import fr.bsm.location.exposition.dto.CountryDto;


@Mapper(componentModel = "spring", uses = {})
public interface CountryDtoMapper {

	CountryDto entityToDto(CountryEntity countryEntity);
	
	CountriesDto entityToCountriesDto(CountriesEntity countriesEntity);
	
}
