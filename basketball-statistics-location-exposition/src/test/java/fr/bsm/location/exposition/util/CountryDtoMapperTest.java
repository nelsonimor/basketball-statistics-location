package fr.bsm.location.exposition.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.country.CountriesEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.common.entity.region.RegionEntity;
import fr.bsm.location.exposition.dto.CountriesDto;
import fr.bsm.location.exposition.dto.CountryDto;

class CountryDtoMapperTest {

	CountryDtoMapper mapper;

	@BeforeEach
	void setUp() {
		mapper = new CountryDtoMapperImpl();
	}

	@Test
	void testEntityToDto() {
		ContinentEntity continent = ContinentEntity.builder().id(1).code("AF").name("Africa").build();
		RegionEntity region = RegionEntity.builder().id(1).name("Balkan").build();
		
		CountryEntity countryEntity = new CountryEntity();
		countryEntity.setId(1);
		countryEntity.setCodeiso2("FR");
		countryEntity.setCodeiso3("FRA");
		countryEntity.setName("France");
		countryEntity.setFullname("France Republic");
		countryEntity.setNumber("123");
		countryEntity.setRegion(region);
		countryEntity.setContinent(continent);

		CountryDto continentDto = mapper.entityToDto(countryEntity);

		assertThat(continentDto).isNotNull().hasNoNullFieldsOrProperties()
		.hasFieldOrPropertyWithValue("id",1)
		.hasFieldOrPropertyWithValue("name","France")
		.hasFieldOrPropertyWithValue("fullname","France Republic")
		.hasFieldOrPropertyWithValue("number","123")
		.hasFieldOrPropertyWithValue("codeiso2","FR")
		.hasFieldOrPropertyWithValue("codeiso3","FRA")
		.hasFieldOrPropertyWithValue("region.id",1)
		.hasFieldOrPropertyWithValue("region.name","Balkan")
		.hasFieldOrPropertyWithValue("continent.id",1)
		.hasFieldOrPropertyWithValue("continent.name","Africa")
		.hasFieldOrPropertyWithValue("continent.code","AF");
	}
	

	@Test
	void testEntitiesToDto() {
		ContinentEntity continent = ContinentEntity.builder().id(1).code("AF").name("Africa").build();
		RegionEntity region = RegionEntity.builder().id(1).name("Balkan").build();
		
		CountryEntity countryEntity = new CountryEntity();
		countryEntity.setId(1);
		countryEntity.setCodeiso2("FR");
		countryEntity.setCodeiso3("FRA");
		countryEntity.setName("France");
		countryEntity.setFullname("France Republic");
		countryEntity.setNumber("123");
		countryEntity.setRegion(region);
		countryEntity.setContinent(continent);
		
		
		
		CountriesEntity countriesEntity = CountriesEntity.builder().items(List.of(countryEntity)).build();
		CountriesDto continentsDto = mapper.entityToCountriesDto(countriesEntity);
		assertThat(continentsDto).isNotNull();
		assertThat(continentsDto.getItems()).hasSize(1);
	}
	
}
