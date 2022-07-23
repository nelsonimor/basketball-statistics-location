package fr.bsm.location.exposition.api.country;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fr.bsm.location.application.country.CountryService;
import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.country.CountriesEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.common.entity.region.RegionEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CountryResourceIntTest {

	@MockBean
	private CountryService countryService;

	@Autowired
	private WebApplicationContext context;

	private MockMvc restMockMvc;

	@Value("classpath:response/countries.json")
	private Resource countriesFile;
	
	@Value("classpath:response/country.json")
	private Resource countryFile;
	
	private final int REGION_ID = 45;
	private final int CONTINENT_ID = 3;
	private final int COUNTRY_ID = 1;
	
	private final String COUNTRY_NAME = "Afghanistan";
	private final String COUNTRY_CODEISO2 = "AF";
	private final String COUNTRY_CODEISO3 = "AFG";

	@BeforeEach
	public void setup() {
		restMockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void getCountriesFindAllSuccess() throws Exception {
		CountriesEntity countries = new CountriesEntity();
		countries.setItems(Arrays.asList(getAfghanistanEntity()));
		when(countryService.findAll(Optional.empty(),Optional.empty())).thenReturn(countries);
		restMockMvc.perform(get("/countries").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json(Files.readString(countriesFile.getFile().toPath()),true));
	}
	
	@Test
	void getCountriesFindByRegionSuccess() throws Exception {
		CountriesEntity countries = new CountriesEntity();
		countries.setItems(Arrays.asList(getAfghanistanEntity()));
		when(countryService.findAll(Optional.empty(),Optional.of(REGION_ID))).thenReturn(countries);
		restMockMvc.perform(get("/countries?regionId="+REGION_ID).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json(Files.readString(countriesFile.getFile().toPath()),true));
	}
	
	@Test
	void getCountriesFindByContinentSuccess() throws Exception {
		CountriesEntity countries = new CountriesEntity();
		countries.setItems(Arrays.asList(getAfghanistanEntity()));
		when(countryService.findAll(Optional.of(CONTINENT_ID),Optional.empty())).thenReturn(countries);
		restMockMvc.perform(get("/countries?continentId="+CONTINENT_ID).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json(Files.readString(countriesFile.getFile().toPath()),true));
	}
	
	@Test
	void getCountriesNoContent() throws Exception {
		CountriesEntity countriesEntity = new CountriesEntity();
		countriesEntity.setItems(new ArrayList<>());
		when(countryService.findAll(Optional.empty(),Optional.empty())).thenReturn(countriesEntity);
		restMockMvc.perform(get("/countries").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}


	@Test
	void getCountryByIdNotFound() throws Exception {
		when(countryService.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		restMockMvc.perform(get("/countries/id/{id}","180")//
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());	
	}
	
	@Test
	void getCountryByIdBadParameter() throws Exception {
		restMockMvc.perform(get("/countries/id/{id}","toto")//
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());	
	}
	
	@Test
	void getCountryByIdFound() throws Exception {
		when(countryService.findById(Mockito.anyInt())).thenReturn(Optional.of(getAfghanistanEntity()));
		restMockMvc.perform(get("/countries/id/{id}",COUNTRY_ID).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json(Files.readString(countryFile.getFile().toPath()),true));
	}
	
	@Test
	void getCountryByNameFound() throws Exception {
		when(countryService.findByName(Mockito.anyString())).thenReturn(Optional.of(getAfghanistanEntity()));
		restMockMvc.perform(get("/countries/name/{name}",COUNTRY_NAME).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json(Files.readString(countryFile.getFile().toPath()),true));
	}
	
	@Test
	void getCountryByCodeiso2Found() throws Exception {
		when(countryService.findByCodeiso2(Mockito.anyString())).thenReturn(Optional.of(getAfghanistanEntity()));
		restMockMvc.perform(get("/countries/codeiso2/{codeiso2}",COUNTRY_CODEISO2).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json(Files.readString(countryFile.getFile().toPath()),true));
	}
	
	@Test
	void getCountryByCodeiso3Found() throws Exception {
		when(countryService.findByCodeiso3(Mockito.anyString())).thenReturn(Optional.of(getAfghanistanEntity()));
		restMockMvc.perform(get("/countries/codeiso3/{codeiso3}",COUNTRY_CODEISO3).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json(Files.readString(countryFile.getFile().toPath()),true));
	}
	
	
	
	
	CountryEntity getAfghanistanEntity() {
		ContinentEntity continentEntity = ContinentEntity.builder().id(CONTINENT_ID).code("AS").name("Asia").build();
		RegionEntity regionEntity = RegionEntity.builder().id(REGION_ID).name("South-Central Asia").build();
		return CountryEntity.builder().id(COUNTRY_ID).codeiso2(COUNTRY_CODEISO2).name(COUNTRY_NAME).codeiso3(COUNTRY_CODEISO3).number("004").fullname("Islamic Republic of Afghanistan").continent(continentEntity).region(regionEntity).build();
		
	}
	
	
	
	
	


}
