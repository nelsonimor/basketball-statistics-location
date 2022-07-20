package fr.basketball.statistics.location.infrastructure.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.basketball.statistics.location.infrastructure.data.region.RegionData;
import fr.bsm.location.domain.common.entity.region.RegionEntity;

@SpringBootTest(classes = RegionEntityMapperImpl.class)
class RegionsEntityMapperTest {
	
	@Autowired
	RegionEntityMapper mapper;
	
	private final Integer REGION_ID = 1;
	private final String REGION_NAME = "Balkan";


	@Test
	void testMapToLabelEntity() {
		RegionData data = RegionData.builder().id(REGION_ID).name(REGION_NAME).build();
		RegionEntity entity = mapper.dataToEntity(data);
		assertRegion(entity, data);
	}

	@Test
	void testEntityToDocument() {
		RegionEntity entity = RegionEntity.builder().id(REGION_ID).name(REGION_NAME).build();
		RegionData data = mapper.entityToData(entity);
		assertRegion(data, entity);
	}

	private void assertRegion(RegionEntity entity, RegionData data) {
		assertThat(entity.getId()).isEqualTo(data.getId());
		assertThat(entity.getName()).isEqualTo(data.getName());
	}

	private void assertRegion(RegionData data, RegionEntity entity) {
		assertThat(data.getId()).isEqualTo(entity.getId());
		assertThat(data.getName()).isEqualTo(entity.getName());
	}

}
