package fr.bsm.location.infrastructure.repository.country;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import fr.bsm.location.domain.common.entity.country.CountriesEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.repository.country.CountryRepository;
import fr.bsm.location.infrastructure.data.continent.ContinentData;
import fr.bsm.location.infrastructure.data.country.CountryData;
import fr.bsm.location.infrastructure.data.region.RegionData;
import fr.bsm.location.infrastructure.repository.continent.ContinentJpaRepository;
import fr.bsm.location.infrastructure.repository.region.RegionJpaRepository;
import fr.bsm.location.infrastructure.util.CountryEntityMapper;
import fr.bsm.location.infrastructure.util.InfrastructureDataUtil;

@DataJpaTest
@EntityScan("fr.bsm.location.infrastructure.data")
@EnableAutoConfiguration
@ContextConfiguration(classes = CountryRepositoryImpl.class)
class CountriesRepositoryTest {


	@Autowired
	CountryRepository repository;

	@MockBean
	CountryEntityMapper countryEntityMapper;
	
	@MockBean
	ContinentJpaRepository continentJpaRepository;
	
	@MockBean
	RegionJpaRepository regionJpaRepository;
	
	Integer idGenerated = 0;


	@BeforeEach
	void dataSetup(@Autowired EntityManager entityManager) {
		entityManager.clear();

		
		RegionData regionData = entityManager.merge(InfrastructureDataUtil.getDataRegionWesternEurope());
		
		ContinentData continentData = entityManager.merge(InfrastructureDataUtil.getDataContinentEurope());

		CountryEntity belgiumEntity = InfrastructureDataUtil.getEntityCountryBelgium();
		CountryData belgiumData = entityManager.merge(InfrastructureDataUtil.getDataCountryBelgium());
		belgiumData.setRegion(regionData);
		belgiumData.setContinent(continentData);
		
		idGenerated = belgiumData.getId();
		
		when(regionJpaRepository.findById(any())).thenReturn(Optional.of(regionData));
		when(continentJpaRepository.findById(any())).thenReturn(Optional.of(continentData));
		when(countryEntityMapper.dataToEntity(any())).thenReturn(belgiumEntity);
	}
	
	@Test
	void testFindById() {
		Optional<CountryEntity> result = repository.findById(idGenerated);
		assertThat(result).isPresent();
		checkCountryContent(result.get());
	}
	
	@Test
	void testFindByName() {
		Optional<CountryEntity> result = repository.findByName(InfrastructureDataUtil.COUNTRY_BELGIUM_NAME);
		assertThat(result).isPresent();
		checkCountryContent(result.get());
	}
	
	@Test
	void testFindByCodeiso2() {
		Optional<CountryEntity> result = repository.findByCodeiso2(InfrastructureDataUtil.COUNTRY_BELGIUM_CODE_ISO2);
		assertThat(result).isPresent();
		checkCountryContent(result.get());
	}
	
	@Test
	void testFindByCodeiso3() {
		Optional<CountryEntity> result = repository.findByCodeiso3(InfrastructureDataUtil.COUNTRY_BELGIUM_CODE_ISO3);
		assertThat(result).isPresent();
		checkCountryContent(result.get());
	}


	@Test
	void testFindAll() {
		CountriesEntity result = repository.findAll(Optional.empty(),Optional.empty());
		assertThat(result).isNotNull();
		assertThat(result.getItems()).isNotNull();
		assertThat(result.getItems()).hasSize(1);
		CountryEntity countryEntity = result.getItems().get(0);
		checkCountryContent(countryEntity);
	}
	
	@Test
	void testFindByContinent() {
		CountriesEntity result = repository.findAll(Optional.of(InfrastructureDataUtil.CONTINENT_EUROPE_ID),Optional.empty());
		assertThat(result).isNotNull();
		assertThat(result.getItems()).isNotNull();
		assertThat(result.getItems()).hasSize(1);
		CountryEntity countryEntity = result.getItems().get(0);
		checkCountryContent(countryEntity);
	}

	@Test
	void testFindByRegion() {
		CountriesEntity result = repository.findAll(Optional.empty(),Optional.of(InfrastructureDataUtil.REGION_WESTERN_EUROPE_ID));
		assertThat(result).isNotNull();
		assertThat(result.getItems()).isNotNull();
		assertThat(result.getItems()).hasSize(1);
		CountryEntity countryEntity = result.getItems().get(0);
		checkCountryContent(countryEntity);
	}
	
	@Test
	void testFindByContinentAndRegion() {
		CountriesEntity result = repository.findAll(Optional.of(InfrastructureDataUtil.CONTINENT_EUROPE_ID),Optional.of(InfrastructureDataUtil.REGION_WESTERN_EUROPE_ID));
		assertThat(result).isNotNull();
		assertThat(result.getItems()).isNotNull();
		assertThat(result.getItems()).hasSize(1);
		CountryEntity countryEntity = result.getItems().get(0);
		checkCountryContent(countryEntity);
	}

	void checkCountryContent(CountryEntity toTest) {
		assertThat(toTest.getId()).isEqualTo(InfrastructureDataUtil.COUNTRY_BELGIUM_ID);
		assertThat(toTest.getName()).isEqualTo(InfrastructureDataUtil.COUNTRY_BELGIUM_NAME);
		assertThat(toTest.getCodeiso2()).isEqualTo(InfrastructureDataUtil.COUNTRY_BELGIUM_CODE_ISO2);
		assertThat(toTest.getCodeiso3()).isEqualTo(InfrastructureDataUtil.COUNTRY_BELGIUM_CODE_ISO3);
		assertThat(toTest.getNumber()).isEqualTo(InfrastructureDataUtil.COUNTRY_BELGIUM_NUMBER);
		
		assertThat(toTest.getContinent().getId()).isEqualTo(InfrastructureDataUtil.CONTINENT_EUROPE_ID);
		assertThat(toTest.getContinent().getName()).isEqualTo(InfrastructureDataUtil.CONTINENT_EUROPE_NAME);
		assertThat(toTest.getContinent().getCode()).isEqualTo(InfrastructureDataUtil.CONTINENT_EUROPE_CODE);
		
		assertThat(toTest.getRegion().getId()).isEqualTo(InfrastructureDataUtil.REGION_WESTERN_EUROPE_ID);
		assertThat(toTest.getRegion().getName()).isEqualTo(InfrastructureDataUtil.REGION_WESTERN_EUROPE_NAME);
	}


}
