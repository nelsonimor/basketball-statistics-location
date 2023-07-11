package fr.bsm.location.exposition.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import fr.bsm.location.domain.common.entity.country.CountriesEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.exposition.dto.CountriesDto;
import fr.bsm.location.exposition.dto.CountryDto;


@Mapper(componentModel = "spring", uses = {})
public interface CountryDtoMapper {

	@Mapping(source = "codeiso2", target = "flagurl", qualifiedByName = "buildFlag")
	CountryDto entityToDto(CountryEntity countryEntity);

	CountriesDto entityToCountriesDto(CountriesEntity countriesEntity);

	@Named("buildFlag")
	public static String buildFlag(String codeiso2) {
		return "http://paulemignoni.alwaysdata.net/flags/16x16/"+codeiso2.toLowerCase()+".png";
	}

}
