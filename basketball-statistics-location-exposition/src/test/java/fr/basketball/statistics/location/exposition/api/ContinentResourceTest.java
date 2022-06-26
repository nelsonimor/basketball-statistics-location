package fr.basketball.statistics.location.exposition.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.basketball.statistics.location.application.continent.ContinentService;
import fr.basketball.statistics.location.application.region.RegionService;
import fr.basketball.statistics.location.domain.common.entity.ContinentEntity;
import fr.basketball.statistics.location.domain.common.entity.ContinentsEntity;
import fr.basketball.statistics.location.exposition.dto.ContinentDto;
import fr.basketball.statistics.location.exposition.dto.ContinentsDto;
import fr.basketball.statistics.location.exposition.util.ContinentDtoMapper;
import fr.basketball.statistics.location.exposition.util.RegionDtoMapper;


@WebMvcTest
class ContinentResourceTest {


	@Autowired
	private MockMvc restMockMvc;

	@MockBean
	private ContinentService continentService;

	@MockBean
	private RegionService regionService;

	@MockBean
	private RegionDtoMapper regionDtoMapper;

	@MockBean
	private ContinentDtoMapper mapperDto;
	
	private Integer CONTINENT_AFRICA_ID = 1;
	private String CONTINENT_AFRICA_NAME = "Africa";
	private String CONTINENT_AFRICA_CODE = "AF";
	
	private Integer CONTINENT_EUROPE_ID = 2;
	private String CONTINENT_EUROPE_NAME = "Europe";
	private String CONTINENT_EUROPE_CODE = "EU";



	@Test
	void testFindAllSuccessful() throws Exception {

		ContinentsEntity continentsEntity = ContinentsEntity.builder()
				.items(createEntityItems())
				.build();

		ContinentsDto continentsDto = ContinentsDto.builder()
				.items(createDtoItems())
				.build();
		when(continentService.findAll()).thenReturn(continentsEntity);
		when(mapperDto.entityToContinentsDto(continentsEntity)).thenReturn(continentsDto);

		restMockMvc.perform(get("/continents")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].id").value(CONTINENT_AFRICA_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].name").value(CONTINENT_AFRICA_NAME))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].code").value(CONTINENT_AFRICA_CODE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[1].id").value(CONTINENT_EUROPE_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[1].name").value(CONTINENT_EUROPE_NAME))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[1].code").value("EU"));
	}

	private List<ContinentEntity> createEntityItems() {
		return Arrays.asList(createContinentEntity(CONTINENT_AFRICA_ID,CONTINENT_AFRICA_NAME,CONTINENT_AFRICA_CODE), createContinentEntity(CONTINENT_EUROPE_ID,CONTINENT_EUROPE_NAME,CONTINENT_EUROPE_CODE));
	}


	private List<ContinentDto> createDtoItems() {
		return Arrays.asList(createContinentDto(CONTINENT_AFRICA_ID,CONTINENT_AFRICA_NAME,CONTINENT_AFRICA_CODE), createContinentDto(CONTINENT_EUROPE_ID,CONTINENT_EUROPE_NAME,CONTINENT_EUROPE_CODE));
	}

	private ContinentDto createContinentDto(Integer id,String name,String code) {
		return ContinentDto.builder()
				.id(id)
				.name(name)
				.code(code)
				.build();
	}

	private ContinentEntity createContinentEntity(Integer id,String name,String code) {
		return ContinentEntity.builder()
				.id(id)
				.name(name)
				.code(code)
				.build();
	}
}
