package fr.bsm.location.infrastructure.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.bsm.location.domain.common.entity.region.RegionEntity;
import fr.bsm.location.infrastructure.data.region.RegionData;

@SpringBootTest(classes = RegionEntityMapperImpl.class)
class RegionsEntityMapperTest {
	
	@Autowired
	RegionEntityMapper mapper;
	
	@Test
	void testMapToLabelEntity() {
		RegionData data = InfrastructureDataUtil.getDataRegionWesternEurope();
		RegionEntity entity = mapper.dataToEntity(data);
		assertRegion(entity, data);
	}

	@Test
	void testEntityToDocument() {
		RegionEntity entity = InfrastructureDataUtil.getEntityRegionWesternEurope();
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
