package fr.bsm.location.exposition.api.city;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.bsm.location.application.city.CityService;
import fr.bsm.location.application.continent.ContinentService;
import fr.bsm.location.application.country.CountryService;
import fr.bsm.location.application.region.RegionService;
import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.common.exception.AlreadyExistException;
import fr.bsm.location.exposition.dto.CitiesDto;
import fr.bsm.location.exposition.dto.CityDto;
import fr.bsm.location.exposition.dto.CityRequestDto;
import fr.bsm.location.exposition.util.CityDtoMapper;
import fr.bsm.location.exposition.util.ContinentDtoMapper;
import fr.bsm.location.exposition.util.CountryDtoMapper;
import fr.bsm.location.exposition.util.ExpositionDataUtil;
import fr.bsm.location.exposition.util.RegionDtoMapper;


@WebMvcTest
class CityResourceTest {


	@Autowired
	private MockMvc restMockMvc;

	@MockBean
	private ContinentService continentService;

	@MockBean
	private RegionService regionService;

	@MockBean
	private CountryService countryService;



	@MockBean
	private ContinentDtoMapper continentMapperDto;

	@MockBean
	private RegionDtoMapper regionMapperDto;

	@MockBean
	private CountryDtoMapper countryMapperDto;


	@MockBean
	private CityService cityService;

	@MockBean
	private CityDtoMapper cityMapperDto;

	@Autowired
	private ObjectMapper objectMapper;



	@Test
	void testFindAllSuccessful() throws Exception {
		CitiesEntity citiesEntity = CitiesEntity.builder()
				.items(Arrays.asList(ExpositionDataUtil.getEntityCityBrussels()))
				.build();

		CitiesDto citiesDto = new CitiesDto();
		citiesDto.setItems(Arrays.asList(ExpositionDataUtil.getDtoCityBrussels()));

		when(cityService.findAll(Optional.empty())).thenReturn(citiesEntity);
		when(cityMapperDto.entityToCitiesDto(citiesEntity)).thenReturn(citiesDto);

		performSuccessFindAll("/cities");
	}

	@Test
	void testFindAllByCountry() throws Exception {
		CitiesEntity citiesEntity = CitiesEntity.builder()
				.items(Arrays.asList(ExpositionDataUtil.getEntityCityBrussels()))
				.build();

		CitiesDto citiesDto = new CitiesDto();
		citiesDto.setItems(Arrays.asList(ExpositionDataUtil.getDtoCityBrussels()));

		when(cityService.findAll(Optional.of(ExpositionDataUtil.COUNTRY_BELGIUM_ID))).thenReturn(citiesEntity);
		when(cityMapperDto.entityToCitiesDto(citiesEntity)).thenReturn(citiesDto);

		performSuccessFindAll("/cities?countryId="+ExpositionDataUtil.COUNTRY_BELGIUM_ID);
	}



	@Test
	void testCreateCitySuccess() throws Exception {
		CityRequestDto cityRequestDto = ExpositionDataUtil.getDtoCityRequestBrussels();
		when(countryService.findByName(any())).thenReturn(Optional.of(ExpositionDataUtil.getEntityCountryBelgium()));
		when(cityService.create(ExpositionDataUtil.getEntityCityBrusselsWithoutGeocoding())).thenReturn(ExpositionDataUtil.getEntityCityBrussels());
		when(cityMapperDto.dtoToEntity(any())).thenReturn(ExpositionDataUtil.getEntityCityBrussels());
		
		restMockMvc.perform(post("/cities")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(cityRequestDto))
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isCreated());
	}
	
	@Test
	void testCreateCityConflict() throws Exception {
		CityRequestDto cityRequestDto = ExpositionDataUtil.getDtoCityRequestBrussels();
		when(countryService.findByName(any())).thenReturn(Optional.of(ExpositionDataUtil.getEntityCountryBelgium()));
		when(cityService.findByNameAndCountry(any(), any())).thenReturn(Optional.of(ExpositionDataUtil.getEntityCityBrussels()));

		restMockMvc.perform(post("/cities")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(cityRequestDto))
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isConflict());
	}

	@Test
	void testFindAllEmpty() throws Exception {
		CitiesEntity citiesEntity = CitiesEntity.builder()
				.items(new ArrayList<CityEntity>())
				.build();


		CitiesDto citiesDto = new CitiesDto();
		citiesDto.setItems(new ArrayList<CityDto>());

		when(cityService.findAll(Optional.empty())).thenReturn(citiesEntity);
		when(cityMapperDto.entityToCitiesDto(citiesEntity)).thenReturn(citiesDto);

		restMockMvc.perform(get("/cities")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
	}

	public void performSuccessFindAll(String query) throws Exception {
		restMockMvc.perform(get(query)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].id").value(ExpositionDataUtil.CITY_BRUSSELS_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].name").value(ExpositionDataUtil.CITY_BRUSSELS_NAME))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].longitude").value(ExpositionDataUtil.CITY_BRUSSELS_LONGITUDE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].latitude").value(ExpositionDataUtil.CITY_BRUSSELS_LATITUDE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].state").value(ExpositionDataUtil.CITY_BRUSSELS_STATE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].county").value(ExpositionDataUtil.CITY_BRUSSELS_COUNTY))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].country.id").value(ExpositionDataUtil.COUNTRY_BELGIUM_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].country.name").value(ExpositionDataUtil.COUNTRY_BELGIUM_NAME))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].country.codeiso2").value(ExpositionDataUtil.COUNTRY_BELGIUM_CODE_ISO2))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].country.codeiso3").value(ExpositionDataUtil.COUNTRY_BELGIUM_CODE_ISO3))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].country.fullname").value(ExpositionDataUtil.COUNTRY_BELGIUM_FULLNAME))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].country.number").value(ExpositionDataUtil.COUNTRY_BELGIUM_NUMBER))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].country.region.id").value(ExpositionDataUtil.REGION_WESTERN_EUROPE_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].country.region.name").value(ExpositionDataUtil.REGION_WESTERN_EUROPE_NAME))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].country.continent.id").value(ExpositionDataUtil.CONTINENT_EUROPE_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].country.continent.name").value(ExpositionDataUtil.CONTINENT_EUROPE_NAME))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].country.continent.code").value(ExpositionDataUtil.CONTINENT_EUROPE_CODE))
		;
	}


}
