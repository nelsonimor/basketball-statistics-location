package fr.basketball.statistics.location.exposition.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.basketball.statistics.location.domain.common.entity.region.RegionEntity;
import fr.basketball.statistics.location.domain.common.entity.region.RegionsEntity;
import fr.basketball.statistics.location.exposition.dto.RegionDto;
import fr.basketball.statistics.location.exposition.dto.RegionsDto;

class RegionDtoMapperTest {

	RegionDtoMapper mapper;

	@BeforeEach
	void setUp() {
		mapper = new RegionDtoMapperImpl();
	}

	@Test
	void testEntityToDto() {
		RegionEntity entity = RegionEntity.builder().id(1).name("Balkan").build();

		RegionDto dto = mapper.entityToDto(entity);

		assertThat(dto).isNotNull().hasNoNullFieldsOrProperties()
		.hasFieldOrPropertyWithValue("id",1)
		.hasFieldOrPropertyWithValue("name","Balkan");
	}

	@Test
	void testEntitiesToDto() {
		RegionsEntity entities = RegionsEntity.builder().items(List.of(RegionEntity.builder().id(1).name("Balkan").build())).build();
		RegionsDto regionsDto = mapper.entityToRegionsDto(entities);
		assertThat(regionsDto).isNotNull();
		assertThat(regionsDto.getItems().size()).isEqualTo(1);
	}
	
}
