package fr.bsm.location.exposition.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.exposition.dto.CitiesDto;
import fr.bsm.location.exposition.dto.CityDto;
import fr.bsm.location.exposition.dto.CountriesDto;

class CityDtoMapperTest {

	CityDtoMapper mapper;

	@BeforeEach
	void setUp() {
		mapper = new CityDtoMapperImpl();
	}

	@Test
	void testEntityToDto() {
		CityDto cityDto = mapper.entityToDto(ExpositionDataUtil.getEntityCityBrussels());

		assertThat(cityDto).isNotNull().hasNoNullFieldsOrProperties()
		.hasFieldOrPropertyWithValue("id",ExpositionDataUtil.CITY_BRUSSELS_ID)
		.hasFieldOrPropertyWithValue("name",ExpositionDataUtil.CITY_BRUSSELS_NAME)
		.hasFieldOrPropertyWithValue("state",ExpositionDataUtil.CITY_BRUSSELS_STATE)
		.hasFieldOrPropertyWithValue("county",ExpositionDataUtil.CITY_BRUSSELS_COUNTY)
		.hasFieldOrPropertyWithValue("longitude",ExpositionDataUtil.CITY_BRUSSELS_LONGITUDE)
		.hasFieldOrPropertyWithValue("latitude",ExpositionDataUtil.CITY_BRUSSELS_LATITUDE)
		.hasFieldOrPropertyWithValue("country.id",ExpositionDataUtil.COUNTRY_BELGIUM_ID)
		.hasFieldOrPropertyWithValue("country.continent.id",ExpositionDataUtil.CONTINENT_EUROPE_ID)
		.hasFieldOrPropertyWithValue("country.region.id",ExpositionDataUtil.REGION_WESTERN_EUROPE_ID);
	}
	

	@Test
	void testEntitiesToDto() {
		CitiesEntity citiesEntity = CitiesEntity.builder().items(List.of(ExpositionDataUtil.getEntityCityBrussels())).build();
		CitiesDto citiesDto = mapper.entityToCitiesDto(citiesEntity);
		assertThat(citiesDto).isNotNull();
		assertThat(citiesDto.getItems()).hasSize(1);
	}
	
}
