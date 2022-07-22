package fr.bsm.location.infrastructure.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.common.entity.region.RegionEntity;
import fr.bsm.location.infrastructure.data.continent.ContinentData;
import fr.bsm.location.infrastructure.data.country.CountryData;
import fr.bsm.location.infrastructure.data.region.RegionData;

@SpringBootTest(classes = CountryEntityMapperImpl.class)
class CountriesEntityMapperTest {
	
	@Autowired
	CountryEntityMapper mapper;
	
	private final Integer COUNTRY_ID = 1;
	private final String COUNTRY_NAME = "Belgium";
	private final String COUNTRY_CODE_ISO2 = "BE";
	private final String COUNTRY_CODE_ISO3 = "BEL";
	private final String COUNTRY_FULLNAME = "Belgium Kingdom";
	private final String COUNTRY_NUMBER = "123";
	
	private final Integer CONTINENT_ID = 1;
	private final String CONTINENT_NAME = "Africa";
	private final String CONTINENT_CODE = "AF";
	
	private final Integer REGION_ID = 1;
	private final String REGION_NAME = "Balkan";

	@Test
	void testMapToLabelEntity() {
		
		ContinentData continentdata = ContinentData.builder().id(CONTINENT_ID).name(CONTINENT_NAME).code(CONTINENT_CODE).build();
		RegionData regiondata = RegionData.builder().id(REGION_ID).name(REGION_NAME).build();
		
		CountryData data = CountryData.builder().id(COUNTRY_ID).name(COUNTRY_NAME).codeiso2(COUNTRY_CODE_ISO2).codeiso3(COUNTRY_CODE_ISO3).fullname(COUNTRY_FULLNAME).number(COUNTRY_NUMBER).continent(continentdata).region(regiondata).build();
		CountryEntity entity = mapper.dataToEntity(data);
		assertCountry(entity, data);
	}

	@Test
	void testEntityToDocument() {
		
		ContinentEntity continententity = ContinentEntity.builder().id(CONTINENT_ID).name(CONTINENT_NAME).code(CONTINENT_CODE).build();
		RegionEntity regionentity = RegionEntity.builder().id(REGION_ID).name(REGION_NAME).build();
		
		CountryEntity entity = CountryEntity.builder().id(COUNTRY_ID).name(COUNTRY_NAME).codeiso2(COUNTRY_CODE_ISO2).codeiso3(COUNTRY_CODE_ISO3).fullname(COUNTRY_FULLNAME).number(COUNTRY_NUMBER).continent(continententity).region(regionentity).build();
		CountryData data = mapper.entityToData(entity);
		assertCountry(data, entity);
	}

	private void assertCountry(CountryEntity entity, CountryData data) {
		assertThat(entity.getId()).isEqualTo(data.getId());
		assertThat(entity.getCodeiso2()).isEqualTo(data.getCodeiso2());
		assertThat(entity.getName()).isEqualTo(data.getName());
		assertThat(entity.getCodeiso3()).isEqualTo(data.getCodeiso3());
		assertThat(entity.getFullname() ).isEqualTo(data.getFullname());
		assertThat(entity.getNumber() ).isEqualTo(data.getNumber());
		
		assertThat(entity.getContinent().getId()).isEqualTo(data.getContinent().getId());
		assertThat(entity.getContinent().getName()).isEqualTo(data.getContinent().getName());
		assertThat(entity.getContinent().getCode()).isEqualTo(data.getContinent().getCode());
		
		assertThat(entity.getRegion().getId()).isEqualTo(data.getRegion().getId());
		assertThat(entity.getRegion().getName()).isEqualTo(data.getRegion().getName());
		
	}

	private void assertCountry(CountryData data, CountryEntity entity) {
		assertThat(data.getId()).isEqualTo(entity.getId());
		assertThat(data.getCodeiso2()).isEqualTo(entity.getCodeiso2());
		assertThat(data.getName()).isEqualTo(entity.getName());
		assertThat(data.getCodeiso3()).isEqualTo(entity.getCodeiso3());
		assertThat(data.getFullname() ).isEqualTo(entity.getFullname());
		assertThat(data.getNumber() ).isEqualTo(entity.getNumber());
		
		assertThat(data.getContinent().getId()).isEqualTo(entity.getContinent().getId());
		assertThat(data.getContinent().getName()).isEqualTo(entity.getContinent().getName());
		assertThat(data.getContinent().getCode()).isEqualTo(entity.getContinent().getCode());
		
		assertThat(data.getRegion().getId()).isEqualTo(entity.getRegion().getId());
		assertThat(data.getRegion().getName()).isEqualTo(entity.getRegion().getName());
	}

}
