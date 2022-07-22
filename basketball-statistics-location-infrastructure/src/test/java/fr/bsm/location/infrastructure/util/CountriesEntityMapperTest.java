package fr.bsm.location.infrastructure.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.infrastructure.data.country.CountryData;

@SpringBootTest(classes = CountryEntityMapperImpl.class)
class CountriesEntityMapperTest {
	
	@Autowired
	CountryEntityMapper mapper;
	
	private final Integer COUNTRY_ID = 1;
	private final String COUNTRY_NAME = "Belgium";
	private final String COUNTRY_CODE_ISO2 = "BE";

	@Test
	void testMapToLabelEntity() {
		CountryData data = CountryData.builder().id(COUNTRY_ID).name(COUNTRY_NAME).codeiso2(COUNTRY_CODE_ISO2).build();
		CountryEntity entity = mapper.dataToEntity(data);
		assertCountry(entity, data);
	}

	@Test
	void testEntityToDocument() {
		CountryEntity entity = CountryEntity.builder().id(COUNTRY_ID).name(COUNTRY_NAME).codeiso2(COUNTRY_CODE_ISO2).build();
		CountryData data = mapper.entityToData(entity);
		assertCountry(data, entity);
	}

	private void assertCountry(CountryEntity entity, CountryData data) {
		assertThat(entity.getId()).isEqualTo(data.getId());
		assertThat(entity.getCodeiso2()).isEqualTo(data.getCodeiso2());
		assertThat(entity.getName()).isEqualTo(data.getName());
	}

	private void assertCountry(CountryData data, CountryEntity entity) {
		assertThat(data.getId()).isEqualTo(entity.getId());
		assertThat(data.getCodeiso2()).isEqualTo(entity.getCodeiso2());
		assertThat(data.getName()).isEqualTo(entity.getName());
	}

}
