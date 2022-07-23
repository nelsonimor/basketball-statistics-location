package fr.bsm.application.country;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.bsm.application.util.ApplicationDataUtil;
import fr.bsm.location.application.country.CountryService;
import fr.bsm.location.application.country.CountryServiceImpl;
import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.continent.ContinentsEntity;
import fr.bsm.location.domain.common.entity.country.CountriesEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.repository.country.CountryRepository;


@ExtendWith(MockitoExtension.class)
class CountryServiceTest {

	@Mock
	CountryRepository countryRepository;

	CountryService countryService;


	@BeforeEach
	void setUp() {
		countryService = new CountryServiceImpl(countryRepository);
	}

	@Test
	void testFindAll() {
		List<CountryEntity> entities = Arrays.asList(ApplicationDataUtil.getCountryBelgium());
		CountriesEntity entityToReturn = CountriesEntity.builder().items(entities).build();
		when(countryRepository.findAll(Optional.empty(),Optional.empty())).thenReturn(entityToReturn);
		CountriesEntity result = countryService.findAll(Optional.empty(),Optional.empty());
		assertThat(result.getItems()).hasSize(1);
		verify(countryRepository).findAll(Optional.empty(),Optional.empty());
	}
	
	@Test
	void testFindByRegion() {
		List<CountryEntity> entities = Arrays.asList(ApplicationDataUtil.getCountryBelgium());
		CountriesEntity entityToReturn = CountriesEntity.builder().items(entities).build();
		when(countryRepository.findAll(Optional.empty(),Optional.of(ApplicationDataUtil.REGION_WESTERN_EUROPE_ID))).thenReturn(entityToReturn);
		CountriesEntity result = countryService.findAll(Optional.empty(),Optional.of(ApplicationDataUtil.REGION_WESTERN_EUROPE_ID));
		assertThat(result.getItems()).hasSize(1);
		verify(countryRepository).findAll(Optional.empty(),Optional.of(ApplicationDataUtil.REGION_WESTERN_EUROPE_ID));
	}
	
	@Test
	void testFindByContinent() {
		List<CountryEntity> entities = Arrays.asList(ApplicationDataUtil.getCountryBelgium());
		CountriesEntity entityToReturn = CountriesEntity.builder().items(entities).build();
		when(countryRepository.findAll(Optional.of(ApplicationDataUtil.CONTINENT_EUROPE_ID),Optional.empty())).thenReturn(entityToReturn);
		CountriesEntity result = countryService.findAll(Optional.of(ApplicationDataUtil.CONTINENT_EUROPE_ID),Optional.empty());
		assertThat(result.getItems()).hasSize(1);
		verify(countryRepository).findAll(Optional.of(ApplicationDataUtil.CONTINENT_EUROPE_ID),Optional.empty());
	}
	
	@Test
	void testFindByRegionAndContinent() {
		List<CountryEntity> entities = Arrays.asList(ApplicationDataUtil.getCountryBelgium());
		CountriesEntity entityToReturn = CountriesEntity.builder().items(entities).build();
		when(countryRepository.findAll(Optional.of(ApplicationDataUtil.CONTINENT_EUROPE_ID),Optional.of(ApplicationDataUtil.REGION_WESTERN_EUROPE_ID))).thenReturn(entityToReturn);
		CountriesEntity result = countryService.findAll(Optional.of(ApplicationDataUtil.CONTINENT_EUROPE_ID),Optional.of(ApplicationDataUtil.REGION_WESTERN_EUROPE_ID));
		assertThat(result.getItems()).hasSize(1);
		verify(countryRepository).findAll(Optional.of(ApplicationDataUtil.CONTINENT_EUROPE_ID),Optional.of(ApplicationDataUtil.REGION_WESTERN_EUROPE_ID));
	}
	
	@Test
	void testFindById() {
		when(countryRepository.findById(ApplicationDataUtil.COUNTRY_BELGIUM_ID)).thenReturn(Optional.of(ApplicationDataUtil.getCountryBelgium()));
		Optional<CountryEntity> result = countryService.findById(ApplicationDataUtil.COUNTRY_BELGIUM_ID);
		assertThat(result).isNotNull();
		assertThat(result.get()).isNotNull();
		assertThat(result.get().getId()).isEqualTo(ApplicationDataUtil.COUNTRY_BELGIUM_ID);
	}
	
	@Test
	void testFindByName() {
		when(countryRepository.findByName(ApplicationDataUtil.COUNTRY_BELGIUM_NAME)).thenReturn(Optional.of(ApplicationDataUtil.getCountryBelgium()));
		Optional<CountryEntity> result = countryService.findByName(ApplicationDataUtil.COUNTRY_BELGIUM_NAME);
		assertThat(result).isNotNull();
		assertThat(result.get()).isNotNull();
		assertThat(result.get().getId()).isEqualTo(ApplicationDataUtil.COUNTRY_BELGIUM_ID);
	}
	
	@Test
	void testFindByCodeIso2() {
		when(countryRepository.findByCodeiso2(ApplicationDataUtil.COUNTRY_BELGIUM_CODE_ISO2)).thenReturn(Optional.of(ApplicationDataUtil.getCountryBelgium()));
		Optional<CountryEntity> result = countryService.findByCodeiso2(ApplicationDataUtil.COUNTRY_BELGIUM_CODE_ISO2);
		assertThat(result).isNotNull();
		assertThat(result.get()).isNotNull();
		assertThat(result.get().getId()).isEqualTo(ApplicationDataUtil.COUNTRY_BELGIUM_ID);
	}
	
	@Test
	void testFindByCodeIso3() {
		when(countryRepository.findByCodeiso3(ApplicationDataUtil.COUNTRY_BELGIUM_CODE_ISO3)).thenReturn(Optional.of(ApplicationDataUtil.getCountryBelgium()));
		Optional<CountryEntity> result = countryService.findByCodeiso3(ApplicationDataUtil.COUNTRY_BELGIUM_CODE_ISO3);
		assertThat(result).isNotNull();
		assertThat(result.get()).isNotNull();
		assertThat(result.get().getId()).isEqualTo(ApplicationDataUtil.COUNTRY_BELGIUM_ID);
	}



//	private List<ContinentEntity> createEntityItems() {
//		return Arrays.asList(createContinentEntity(CONTINENT_AFRICA_ID,CONTINENT_AFRICA_NAME,CONTINENT_AFRICA_CODE), createContinentEntity(CONTINENT_EUROPE_ID,CONTINENT_EUROPE_NAME,CONTINENT_EUROPE_CODE));
//	}
//	
//	private ContinentEntity createContinentEntity(Integer id,String name,String code) {
//		return ContinentEntity.builder()
//				.id(id)
//				.name(name)
//				.code(code)
//				.build();
//	}


}
