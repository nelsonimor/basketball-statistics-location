package fr.bsm.location.exposition.api.city;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.bsm.location.application.city.CityService;
import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.exposition.dto.CityRequestDto;
import fr.bsm.location.exposition.util.ExpositionDataUtil;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CityResourceIntTest {

	@MockBean
	private CityService cityService;

	@Autowired
	private WebApplicationContext context;

	private MockMvc restMockMvc;

	@Value("classpath:response/cities.json")
	private Resource citiesFile;

	@Value("classpath:response/city_creation.json")
	private Resource cityCreationFile;

	@Autowired
	private ObjectMapper objectMapper;
	
	@BeforeEach
	public void setup() {
		restMockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void getAllCitiesSuccess() throws Exception {
		CitiesEntity citiesEntity = new CitiesEntity();
		citiesEntity.setItems(Arrays.asList(ExpositionDataUtil.getEntityCityBrussels()));
		when(cityService.findAll(Optional.empty())).thenReturn(citiesEntity);
		restMockMvc.perform(get("/cities").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json(Files.readString(citiesFile.getFile().toPath()),true));
	}

	@Test
	void getCitiesByRegionSuccess() throws Exception {
		CitiesEntity citiesEntity = new CitiesEntity();
		citiesEntity.setItems(Arrays.asList(ExpositionDataUtil.getEntityCityBrussels()));
		when(cityService.findAll(Optional.of(ExpositionDataUtil.COUNTRY_BELGIUM_ID))).thenReturn(citiesEntity);
		restMockMvc.perform(get("/cities?countryId="+ExpositionDataUtil.COUNTRY_BELGIUM_ID).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json(Files.readString(citiesFile.getFile().toPath()),true));
	}

	@Test
	void getCitiesNoContent() throws Exception {
		CitiesEntity citiesEntity = new CitiesEntity();
		citiesEntity.setItems(new ArrayList<>());
		when(cityService.findAll(Optional.empty())).thenReturn(citiesEntity);
		restMockMvc.perform(get("/cities").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}


	@Test
	void testCreateCity() throws Exception {
		CityRequestDto cityRequestDto = ExpositionDataUtil.getDtoCityRequestBrussels();
		when(cityService.create(ExpositionDataUtil.getEntityCityBrusselsWithoutGeocoding())).thenReturn(ExpositionDataUtil.getEntityCityBrussels());

		restMockMvc.perform(post("/cities")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(cityRequestDto))
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isCreated());
	}










}
