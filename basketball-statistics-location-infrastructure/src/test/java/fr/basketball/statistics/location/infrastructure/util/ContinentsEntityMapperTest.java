package fr.basketball.statistics.location.infrastructure.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.basketball.statistics.location.domain.common.entity.ContinentEntity;
import fr.basketball.statistics.location.infrastructure.data.ContinentData;

@SpringBootTest(classes = ContinentEntityMapperImpl.class)
class ContinentsEntityMapperTest {
	
	@Autowired
	ContinentEntityMapper mapper;
	
	private final Integer CONTINENT_ID = 1;
	private final String CONTINENT_NAME = "Africa";
	private final String CONTINENT_CODE = "AF";

	@Test
	void testMapToLabelEntity() {
		ContinentData data = ContinentData.builder().id(CONTINENT_ID).name(CONTINENT_NAME).code(CONTINENT_CODE).build();
		ContinentEntity entity = mapper.dataToEntity(data);
		assertContinent(entity, data);
	}

	@Test
	void testEntityToDocument() {
		ContinentEntity entity = ContinentEntity.builder().id(CONTINENT_ID).name(CONTINENT_NAME).code(CONTINENT_CODE).build();
		ContinentData data = mapper.entityToData(entity);
		assertContinent(data, entity);
	}

	private void assertContinent(ContinentEntity entity, ContinentData data) {
		assertThat(entity.getId()).isEqualTo(data.getId());
		assertThat(entity.getCode()).isEqualTo(data.getCode());
		assertThat(entity.getName()).isEqualTo(data.getName());
	}

	private void assertContinent(ContinentData data, ContinentEntity entity) {
		assertThat(data.getId()).isEqualTo(entity.getId());
		assertThat(data.getCode()).isEqualTo(entity.getCode());
		assertThat(data.getName()).isEqualTo(entity.getName());
	}

}
