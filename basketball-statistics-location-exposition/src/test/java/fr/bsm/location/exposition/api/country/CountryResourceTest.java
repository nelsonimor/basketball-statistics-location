package fr.bsm.location.exposition.api.country;

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

import fr.bsm.location.application.city.CityService;
import fr.bsm.location.application.continent.ContinentService;
import fr.bsm.location.application.country.CountryService;
import fr.bsm.location.application.region.RegionService;
import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.country.CountriesEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.common.entity.region.RegionEntity;
import fr.bsm.location.exposition.dto.ContinentDto;
import fr.bsm.location.exposition.dto.CountriesDto;
import fr.bsm.location.exposition.dto.CountryDto;
import fr.bsm.location.exposition.dto.RegionDto;
import fr.bsm.location.exposition.util.CityDtoMapper;
import fr.bsm.location.exposition.util.ContinentDtoMapper;
import fr.bsm.location.exposition.util.CountryDtoMapper;
import fr.bsm.location.exposition.util.RegionDtoMapper;


@WebMvcTest
class CountryResourceTest {


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

	private Integer CONTINENT_EUROPE_ID = 2;
	private String CONTINENT_EUROPE_NAME = "Europe";
	private String CONTINENT_EUROPE_CODE = "EU";
	
	private Integer REGION_WESTERN_EUROPE_ID = 1;
	private String REGION_WESTERN_EUROPE_NAME = "Western Europe";

	private final Integer COUNTRY_BELGIUM_ID = 1;
	private final String COUNTRY_BELGIUM_NAME = "Belgium";
	private final String COUNTRY_BELGIUM_CODE_ISO2 = "BE";
	private final String COUNTRY_BELGIUM_CODE_ISO3 = "BEL";
	private final String COUNTRY_BELGIUM_FULLNAME = "Belgium Kingdom";
	private final String COUNTRY_BELGIUM_NUMBER = "123";

	@Test
	void testFindAllSuccessful() throws Exception {

		CountriesEntity countriesEntity = CountriesEntity.builder()
				.items(createEntityItems())
				.build();

		CountriesDto countriesDto = new CountriesDto();
		countriesDto.setItems(createDtoItems());

		when(countryService.findAll(Optional.empty(),Optional.empty())).thenReturn(countriesEntity);
		when(countryMapperDto.entityToCountriesDto(countriesEntity)).thenReturn(countriesDto);

		performSuccessFindAll("/countries");
	}
	
	@Test
	void testFindByRegionSuccessful() throws Exception {

		CountriesEntity countriesEntity = CountriesEntity.builder()
				.items(createEntityItems())
				.build();

		CountriesDto countriesDto = new CountriesDto();
		countriesDto.setItems(createDtoItems());

		when(countryService.findAll(Optional.empty(),Optional.of(REGION_WESTERN_EUROPE_ID))).thenReturn(countriesEntity);
		when(countryMapperDto.entityToCountriesDto(countriesEntity)).thenReturn(countriesDto);
		
		performSuccessFindAll("/countries?regionId="+REGION_WESTERN_EUROPE_ID);
	}
	
	@Test
	void testFindByContinentSuccessful() throws Exception {

		CountriesEntity countriesEntity = CountriesEntity.builder()
				.items(createEntityItems())
				.build();

		CountriesDto countriesDto = new CountriesDto();
		countriesDto.setItems(createDtoItems());

		when(countryService.findAll(Optional.of(CONTINENT_EUROPE_ID),Optional.empty())).thenReturn(countriesEntity);
		when(countryMapperDto.entityToCountriesDto(countriesEntity)).thenReturn(countriesDto);

		performSuccessFindAll("/countries?continentId="+CONTINENT_EUROPE_ID);
	
	}
	
	@Test
	void testFindByRegionAndContinentSuccessful() throws Exception {

		CountriesEntity countriesEntity = CountriesEntity.builder()
				.items(createEntityItems())
				.build();

		CountriesDto countriesDto = new CountriesDto();
		countriesDto.setItems(createDtoItems());

		when(countryService.findAll(Optional.of(CONTINENT_EUROPE_ID),Optional.of(REGION_WESTERN_EUROPE_ID))).thenReturn(countriesEntity);
		when(countryMapperDto.entityToCountriesDto(countriesEntity)).thenReturn(countriesDto);

		performSuccessFindAll("/countries?continentId="+CONTINENT_EUROPE_ID+"&regionId="+REGION_WESTERN_EUROPE_ID);
	}
	
	@Test
	void testFindAllEmpty() throws Exception {
		CountriesEntity countriesEntity = CountriesEntity.builder()
				.items(new ArrayList<CountryEntity>())
				.build();

		
		CountriesDto countriesDto = new CountriesDto();
		countriesDto.setItems(new ArrayList<CountryDto>());
		
		when(countryService.findAll(Optional.empty(),Optional.empty())).thenReturn(countriesEntity);
		when(countryMapperDto.entityToCountriesDto(countriesEntity)).thenReturn(countriesDto);

		restMockMvc.perform(get("/countries")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
	}
	
	@Test
	void testFindByIdSuccessful() throws Exception {
		CountryEntity belgiumEntity = createCountryEntity(COUNTRY_BELGIUM_ID,COUNTRY_BELGIUM_NAME,COUNTRY_BELGIUM_CODE_ISO2,COUNTRY_BELGIUM_CODE_ISO3,COUNTRY_BELGIUM_FULLNAME,COUNTRY_BELGIUM_NUMBER,REGION_WESTERN_EUROPE_ID,REGION_WESTERN_EUROPE_NAME,CONTINENT_EUROPE_ID,CONTINENT_EUROPE_NAME,CONTINENT_EUROPE_CODE);

		CountryDto belgiumDto = createCountryDto(COUNTRY_BELGIUM_ID,COUNTRY_BELGIUM_NAME,COUNTRY_BELGIUM_CODE_ISO2,COUNTRY_BELGIUM_CODE_ISO3,COUNTRY_BELGIUM_FULLNAME,COUNTRY_BELGIUM_NUMBER,REGION_WESTERN_EUROPE_ID,REGION_WESTERN_EUROPE_NAME,CONTINENT_EUROPE_ID,CONTINENT_EUROPE_NAME,CONTINENT_EUROPE_CODE);
		when(countryService.findById(COUNTRY_BELGIUM_ID)).thenReturn(Optional.of(belgiumEntity));
		when(countryMapperDto.entityToDto(belgiumEntity)).thenReturn(belgiumDto);

		performSuccessFindOne("/countries/id/"+COUNTRY_BELGIUM_ID);

	}
	
	@Test
	void testFindByNameSuccessful() throws Exception {
		CountryEntity belgiumEntity = createCountryEntity(COUNTRY_BELGIUM_ID,COUNTRY_BELGIUM_NAME,COUNTRY_BELGIUM_CODE_ISO2,COUNTRY_BELGIUM_CODE_ISO3,COUNTRY_BELGIUM_FULLNAME,COUNTRY_BELGIUM_NUMBER,REGION_WESTERN_EUROPE_ID,REGION_WESTERN_EUROPE_NAME,CONTINENT_EUROPE_ID,CONTINENT_EUROPE_NAME,CONTINENT_EUROPE_CODE);

		CountryDto belgiumDto = createCountryDto(COUNTRY_BELGIUM_ID,COUNTRY_BELGIUM_NAME,COUNTRY_BELGIUM_CODE_ISO2,COUNTRY_BELGIUM_CODE_ISO3,COUNTRY_BELGIUM_FULLNAME,COUNTRY_BELGIUM_NUMBER,REGION_WESTERN_EUROPE_ID,REGION_WESTERN_EUROPE_NAME,CONTINENT_EUROPE_ID,CONTINENT_EUROPE_NAME,CONTINENT_EUROPE_CODE);
		when(countryService.findByName(COUNTRY_BELGIUM_NAME)).thenReturn(Optional.of(belgiumEntity));
		when(countryMapperDto.entityToDto(belgiumEntity)).thenReturn(belgiumDto);

		performSuccessFindOne("/countries/name/"+COUNTRY_BELGIUM_NAME);
	}
	
	@Test
	void testFindByCodeIso2Successful() throws Exception {
		CountryEntity belgiumEntity = createCountryEntity(COUNTRY_BELGIUM_ID,COUNTRY_BELGIUM_NAME,COUNTRY_BELGIUM_CODE_ISO2,COUNTRY_BELGIUM_CODE_ISO3,COUNTRY_BELGIUM_FULLNAME,COUNTRY_BELGIUM_NUMBER,REGION_WESTERN_EUROPE_ID,REGION_WESTERN_EUROPE_NAME,CONTINENT_EUROPE_ID,CONTINENT_EUROPE_NAME,CONTINENT_EUROPE_CODE);

		CountryDto belgiumDto = createCountryDto(COUNTRY_BELGIUM_ID,COUNTRY_BELGIUM_NAME,COUNTRY_BELGIUM_CODE_ISO2,COUNTRY_BELGIUM_CODE_ISO3,COUNTRY_BELGIUM_FULLNAME,COUNTRY_BELGIUM_NUMBER,REGION_WESTERN_EUROPE_ID,REGION_WESTERN_EUROPE_NAME,CONTINENT_EUROPE_ID,CONTINENT_EUROPE_NAME,CONTINENT_EUROPE_CODE);
		when(countryService.findByCodeiso2(COUNTRY_BELGIUM_CODE_ISO2)).thenReturn(Optional.of(belgiumEntity));
		when(countryMapperDto.entityToDto(belgiumEntity)).thenReturn(belgiumDto);

		performSuccessFindOne("/countries/codeiso2/"+COUNTRY_BELGIUM_CODE_ISO2);

	}
	
	@Test
	void testFindByCodeIso3Successful() throws Exception {
		CountryEntity belgiumEntity = createCountryEntity(COUNTRY_BELGIUM_ID,COUNTRY_BELGIUM_NAME,COUNTRY_BELGIUM_CODE_ISO2,COUNTRY_BELGIUM_CODE_ISO3,COUNTRY_BELGIUM_FULLNAME,COUNTRY_BELGIUM_NUMBER,REGION_WESTERN_EUROPE_ID,REGION_WESTERN_EUROPE_NAME,CONTINENT_EUROPE_ID,CONTINENT_EUROPE_NAME,CONTINENT_EUROPE_CODE);

		CountryDto belgiumDto = createCountryDto(COUNTRY_BELGIUM_ID,COUNTRY_BELGIUM_NAME,COUNTRY_BELGIUM_CODE_ISO2,COUNTRY_BELGIUM_CODE_ISO3,COUNTRY_BELGIUM_FULLNAME,COUNTRY_BELGIUM_NUMBER,REGION_WESTERN_EUROPE_ID,REGION_WESTERN_EUROPE_NAME,CONTINENT_EUROPE_ID,CONTINENT_EUROPE_NAME,CONTINENT_EUROPE_CODE);
		when(countryService.findByCodeiso3(COUNTRY_BELGIUM_CODE_ISO3)).thenReturn(Optional.of(belgiumEntity));
		when(countryMapperDto.entityToDto(belgiumEntity)).thenReturn(belgiumDto);

		performSuccessFindOne("/countries/codeiso3/"+COUNTRY_BELGIUM_CODE_ISO3);

	}
	
	@Test
	void testFindByIdNotFound() throws Exception {
		when(countryService.findById(19)).thenReturn(Optional.empty());


		restMockMvc.perform(get("/countries/19")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void testFindByNameNotFound() throws Exception {
		when(countryService.findByName("Zombie")).thenReturn(Optional.empty());


		restMockMvc.perform(get("/countries/Zombie")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void testFindByCodeIso2NotFound() throws Exception {
		when(countryService.findByCodeiso2("TT")).thenReturn(Optional.empty());


		restMockMvc.perform(get("/countries/TT")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void testFindByCodeIso3NotFound() throws Exception {
		when(countryService.findByCodeiso3("TTT")).thenReturn(Optional.empty());


		restMockMvc.perform(get("/countries/TTT")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void testFindByIdBadParameter() throws Exception {
		restMockMvc.perform(get("/countries/id/toto")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}

	private List<CountryEntity> createEntityItems() {
		return Arrays.asList(createCountryEntity(COUNTRY_BELGIUM_ID,COUNTRY_BELGIUM_NAME,COUNTRY_BELGIUM_CODE_ISO2,COUNTRY_BELGIUM_CODE_ISO3,COUNTRY_BELGIUM_FULLNAME,COUNTRY_BELGIUM_NUMBER,REGION_WESTERN_EUROPE_ID,REGION_WESTERN_EUROPE_NAME,CONTINENT_EUROPE_ID,CONTINENT_EUROPE_NAME,CONTINENT_EUROPE_CODE));
	}


	private List<CountryDto> createDtoItems() {
		return Arrays.asList(createCountryDto(COUNTRY_BELGIUM_ID,COUNTRY_BELGIUM_NAME,COUNTRY_BELGIUM_CODE_ISO2,COUNTRY_BELGIUM_CODE_ISO3,COUNTRY_BELGIUM_FULLNAME,COUNTRY_BELGIUM_NUMBER,REGION_WESTERN_EUROPE_ID,REGION_WESTERN_EUROPE_NAME,CONTINENT_EUROPE_ID,CONTINENT_EUROPE_NAME,CONTINENT_EUROPE_CODE));
	}

	private CountryDto createCountryDto(Integer id,String name,String codeiso2,String codeiso3,String fullname,String number,Integer regionId,String regionName,Integer continentId,String continentName,String continentCode) {
		RegionDto regiondto= new RegionDto();
		regiondto.setId(regionId);
		regiondto.setName(regionName);
		
		ContinentDto continentdto = new ContinentDto();
		continentdto.setId(continentId);
		continentdto.setName(continentName);
		continentdto.setCode(continentCode);
		
		CountryDto countryDto = new CountryDto();
		countryDto.setId(id);
		countryDto.setCodeiso2(codeiso2);
		countryDto.setCodeiso3(codeiso3);
		countryDto.setName(name);
		countryDto.setFullname(fullname);
		countryDto.setNumber(number);
		countryDto.setRegion(regiondto);
		countryDto.setContinent(continentdto);
		return countryDto;
	}

	private CountryEntity createCountryEntity(Integer id,String name,String codeiso2,String codeiso3,String fullname,String number,Integer regionId,String regionName,Integer continentId,String continentName,String continentCode) {
		ContinentEntity continententity = ContinentEntity.builder().id(continentId).name(continentName).code(continentCode).build();
		RegionEntity regionentity = RegionEntity.builder().id(regionId).name(regionName).build();
		return CountryEntity.builder().id(id).name(name).codeiso2(codeiso2).codeiso3(codeiso3).fullname(fullname).number(number).continent(continententity).region(regionentity).build();
	}
	
	public void performSuccessFindAll(String query) throws Exception {
		restMockMvc.perform(get(query)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].id").value(COUNTRY_BELGIUM_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].name").value(COUNTRY_BELGIUM_NAME))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].codeiso2").value(COUNTRY_BELGIUM_CODE_ISO2))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].codeiso3").value(COUNTRY_BELGIUM_CODE_ISO3))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].fullname").value(COUNTRY_BELGIUM_FULLNAME))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].number").value(COUNTRY_BELGIUM_NUMBER))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].region.id").value(REGION_WESTERN_EUROPE_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].region.name").value(REGION_WESTERN_EUROPE_NAME))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].continent.id").value(CONTINENT_EUROPE_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].continent.name").value(CONTINENT_EUROPE_NAME))
		.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].continent.code").value(CONTINENT_EUROPE_CODE))
		;
	}
	
	public void performSuccessFindOne(String query) throws Exception {
		restMockMvc.perform(get(query)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(COUNTRY_BELGIUM_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(COUNTRY_BELGIUM_NAME))
		.andExpect(MockMvcResultMatchers.jsonPath("$.codeiso2").value(COUNTRY_BELGIUM_CODE_ISO2))
		.andExpect(MockMvcResultMatchers.jsonPath("$.codeiso3").value(COUNTRY_BELGIUM_CODE_ISO3))
		.andExpect(MockMvcResultMatchers.jsonPath("$.fullname").value(COUNTRY_BELGIUM_FULLNAME))
		.andExpect(MockMvcResultMatchers.jsonPath("$.number").value(COUNTRY_BELGIUM_NUMBER))
		.andExpect(MockMvcResultMatchers.jsonPath("$.region.id").value(REGION_WESTERN_EUROPE_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$.region.name").value(REGION_WESTERN_EUROPE_NAME))
		.andExpect(MockMvcResultMatchers.jsonPath("$.continent.id").value(CONTINENT_EUROPE_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$.continent.name").value(CONTINENT_EUROPE_NAME))
		.andExpect(MockMvcResultMatchers.jsonPath("$.continent.code").value(CONTINENT_EUROPE_CODE));
	}
	
	
}
