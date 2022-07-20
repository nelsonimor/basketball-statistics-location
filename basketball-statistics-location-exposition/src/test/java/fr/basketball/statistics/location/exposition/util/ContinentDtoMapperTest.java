package fr.basketball.statistics.location.exposition.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.basketball.statistics.location.exposition.dto.ContinentDto;
import fr.basketball.statistics.location.exposition.dto.ContinentsDto;
import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.continent.ContinentsEntity;

class ContinentDtoMapperTest {

	ContinentDtoMapper mapper;

	@BeforeEach
	void setUp() {
		mapper = new ContinentDtoMapperImpl();
	}

	@Test
	void testEntityToDto() {
		ContinentEntity continentEntity = ContinentEntity.builder().id(1).code("AF").name("Africa").build();

		ContinentDto continentDto = mapper.entityToDto(continentEntity);

		assertThat(continentDto).isNotNull().hasNoNullFieldsOrProperties()
		.hasFieldOrPropertyWithValue("id",1)
		.hasFieldOrPropertyWithValue("name","Africa")
		.hasFieldOrPropertyWithValue("code","AF");
	}

	@Test
	void testEntitiesToDto() {
		ContinentsEntity continentsEntity = ContinentsEntity.builder().items(List.of(ContinentEntity.builder().id(1).code("AF").name("Africa").build())).build();
		ContinentsDto continentsDto = mapper.entityToContinentsDto(continentsEntity);
		assertThat(continentsDto).isNotNull();
		assertThat(continentsDto.getItems()).hasSize(1);
	}
	
}
