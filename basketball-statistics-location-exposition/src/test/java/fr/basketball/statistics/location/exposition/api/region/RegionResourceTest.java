package fr.basketball.statistics.location.exposition.api.region;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.basketball.statistics.location.application.continent.ContinentService;
import fr.basketball.statistics.location.application.region.RegionService;
import fr.basketball.statistics.location.domain.common.entity.region.RegionEntity;
import fr.basketball.statistics.location.domain.common.entity.region.RegionsEntity;
import fr.basketball.statistics.location.exposition.dto.RegionDto;
import fr.basketball.statistics.location.exposition.dto.RegionsDto;
import fr.basketball.statistics.location.exposition.util.ContinentDtoMapper;
import fr.basketball.statistics.location.exposition.util.RegionDtoMapper;


@WebMvcTest
class RegionResourceTest {


	@Autowired
	private MockMvc restMockMvc;

	@MockBean
	private ContinentService continentService;

	@MockBean
	private RegionService regionService;

	@MockBean
	private ContinentDtoMapper continentMapperDto;
	
	@MockBean
	private RegionDtoMapper regionMapperDto;
	
	private Integer REGION_BALKAN_ID = 1;
	private String REGION_BALKAN_NAME = "Balkan";
	
	private Integer REGION_CAUCASUS_ID = 2;
	private String REGION_CAUCASUS_NAME = "Caucasus";



	@Test
	void testFindAllSuccessful() throws Exception {

		RegionsEntity regionsEntity = RegionsEntity.builder()
				.items(createEntityItems())
				.build();

		
		
		RegionsDto regionsDto = new RegionsDto();
		regionsDto.setItems(createDtoItems());

		when(regionService.findAll()).thenReturn(regionsEntity);
		when(regionMapperDto.entityToRegionsDto(regionsEntity)).thenReturn(regionsDto);

		restMockMvc.perform(get("/regions")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].id").value(REGION_BALKAN_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].name").value(REGION_BALKAN_NAME))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[1].id").value(REGION_CAUCASUS_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[1].name").value(REGION_CAUCASUS_NAME));
	}
	
	@Test
	void testFindAllEmpty() throws Exception {
		RegionsEntity regionsEntity = RegionsEntity.builder()
				.items(new ArrayList<RegionEntity>())
				.build();

		
		RegionsDto regionsDto = new RegionsDto();
		regionsDto.setItems(new ArrayList<RegionDto>());
		
		when(regionService.findAll()).thenReturn(regionsEntity);
		when(regionMapperDto.entityToRegionsDto(regionsEntity)).thenReturn(regionsDto);

		restMockMvc.perform(get("/regions")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
	}
	
	@Test
	void testFindByIdSuccessful() throws Exception {
		RegionEntity entity = createEntity(REGION_CAUCASUS_ID,REGION_CAUCASUS_NAME);

		RegionDto regionDto = createDto(REGION_CAUCASUS_ID, REGION_CAUCASUS_NAME);
		when(regionService.findById(REGION_CAUCASUS_ID)).thenReturn(Optional.of(entity));
		when(regionMapperDto.entityToDto(entity)).thenReturn(regionDto);

		restMockMvc.perform(get("/regions/"+REGION_CAUCASUS_ID)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(REGION_CAUCASUS_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(REGION_CAUCASUS_NAME));
	}
	
	@Test
	void testFindByIdNotFound() throws Exception {
		when(regionService.findById(19)).thenReturn(Optional.empty());


		restMockMvc.perform(get("/regions/19")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void testFindByIdBadParameter() throws Exception {
		restMockMvc.perform(get("/regions/toto")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}

	private List<RegionEntity> createEntityItems() {
		return Arrays.asList(createEntity(REGION_BALKAN_ID,REGION_BALKAN_NAME), createEntity(REGION_CAUCASUS_ID,REGION_CAUCASUS_NAME));
	}


	private List<RegionDto> createDtoItems() {
		return Arrays.asList(createDto(REGION_BALKAN_ID,REGION_BALKAN_NAME), createDto(REGION_CAUCASUS_ID,REGION_CAUCASUS_NAME));
	}

	private RegionDto createDto(Integer id,String name) {
		RegionDto dto = new RegionDto();
		dto.setId(id);
		dto.setName(name);
		return dto;
	}

	private RegionEntity createEntity(Integer id,String name) {
		return RegionEntity.builder()
				.id(id)
				.name(name)
				.build();
	}
}
