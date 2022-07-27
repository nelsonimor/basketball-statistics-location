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
	

	@Test
	void testMapToLabelEntity() {
		CountryData data = InfrastructureDataUtil.getDataCountryBelgium();
		CountryEntity entity = mapper.dataToEntity(data);
		assertCountry(entity, data);
	}

	@Test
	void testEntityToDocument() {	
		CountryEntity entity = InfrastructureDataUtil.getEntityCountryBelgium();
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
