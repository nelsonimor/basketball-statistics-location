package fr.bsm.location.infrastructure.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.infrastructure.data.city.CityData;

@SpringBootTest(classes = CityEntityMapperImpl.class)
class CitiesEntityMapperTest {

	@Autowired
	CityEntityMapper mapper;


	@Test
	void testMapToLabelEntity() {
		CityData data = InfrastructureDataUtil.getDataCityBrussels();
		CityEntity entity = mapper.dataToEntity(data);
		assertCity(entity, data);
	}

	@Test
	void testEntityToDocument() {	
		CityEntity entity = InfrastructureDataUtil.getEntityCityBrussels();
		CityData data = mapper.entityToData(entity);
		assertCity(data, entity);
	}

	private void assertCity(CityEntity entity, CityData data) {
		assertThat(entity.getId()).isEqualTo(data.getId());
		assertThat(entity.getName()).isEqualTo(data.getName());
		assertThat(entity.getLongitude()).isEqualTo(data.getLongitude());
		assertThat(entity.getLatitude()).isEqualTo(data.getLatitude());
		assertThat(entity.getState()).isEqualTo(data.getState());
		assertThat(entity.getCounty()).isEqualTo(data.getCounty());

		assertThat(entity.getCountry().getId()).isEqualTo(data.getCountry().getId());
		assertThat(entity.getCountry().getCodeiso2()).isEqualTo(data.getCountry().getCodeiso2());
		assertThat(entity.getCountry().getName()).isEqualTo(data.getCountry().getName());
		assertThat(entity.getCountry().getCodeiso3()).isEqualTo(data.getCountry().getCodeiso3());
		assertThat(entity.getCountry().getFullname() ).isEqualTo(data.getCountry().getFullname());
		assertThat(entity.getCountry().getNumber() ).isEqualTo(data.getCountry().getNumber());

		assertThat(entity.getCountry().getContinent().getId()).isEqualTo(data.getCountry().getContinent().getId());
		assertThat(entity.getCountry().getContinent().getName()).isEqualTo(data.getCountry().getContinent().getName());
		assertThat(entity.getCountry().getContinent().getCode()).isEqualTo(data.getCountry().getContinent().getCode());

		assertThat(entity.getCountry().getRegion().getId()).isEqualTo(data.getCountry().getRegion().getId());
		assertThat(entity.getCountry().getRegion().getName()).isEqualTo(data.getCountry().getRegion().getName());

	}

	private void assertCity(CityData data, CityEntity entity) {
		assertThat(entity.getId()).isEqualTo(data.getId());
		assertThat(entity.getName()).isEqualTo(data.getName());
		assertThat(entity.getLongitude()).isEqualTo(data.getLongitude());
		assertThat(entity.getLatitude()).isEqualTo(data.getLatitude());
		assertThat(entity.getState()).isEqualTo(data.getState());
		assertThat(entity.getCounty()).isEqualTo(data.getCounty());
		
		assertThat(data.getCountry().getId()).isEqualTo(entity.getCountry().getId());
		assertThat(data.getCountry().getCodeiso2()).isEqualTo(entity.getCountry().getCodeiso2());
		assertThat(data.getCountry().getName()).isEqualTo(entity.getCountry().getName());
		assertThat(data.getCountry().getCodeiso3()).isEqualTo(entity.getCountry().getCodeiso3());
		assertThat(data.getCountry().getFullname() ).isEqualTo(entity.getCountry().getFullname());
		assertThat(data.getCountry().getNumber() ).isEqualTo(entity.getCountry().getNumber());

		assertThat(data.getCountry().getContinent().getId()).isEqualTo(entity.getCountry().getContinent().getId());
		assertThat(data.getCountry().getContinent().getName()).isEqualTo(entity.getCountry().getContinent().getName());
		assertThat(data.getCountry().getContinent().getCode()).isEqualTo(entity.getCountry().getContinent().getCode());

		assertThat(data.getCountry().getRegion().getId()).isEqualTo(entity.getCountry().getRegion().getId());
		assertThat(data.getCountry().getRegion().getName()).isEqualTo(entity.getCountry().getRegion().getName());
	}

}
