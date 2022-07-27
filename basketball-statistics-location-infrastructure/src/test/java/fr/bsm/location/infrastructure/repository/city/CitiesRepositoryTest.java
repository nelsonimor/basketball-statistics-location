package fr.bsm.location.infrastructure.repository.city;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.repository.city.CityRepository;
import fr.bsm.location.infrastructure.data.city.CityData;
import fr.bsm.location.infrastructure.data.country.CountryData;
import fr.bsm.location.infrastructure.repository.country.CountryJpaRepository;
import fr.bsm.location.infrastructure.util.CityEntityMapper;
import fr.bsm.location.infrastructure.util.InfrastructureDataUtil;

@DataJpaTest
@EntityScan("fr.bsm.location.infrastructure.data")
@EnableAutoConfiguration
@ContextConfiguration(classes = CityRepositoryImpl.class)
class CitiesRepositoryTest {


	@Autowired
	CityRepository repository;

	@MockBean
	CityEntityMapper continentEntityMapper;
	
	@MockBean
	CountryJpaRepository countryJpaRepository;
	
	Integer idGenerated = 0;


	@BeforeEach
	void dataSetup(@Autowired EntityManager entityManager) {
		entityManager.clear();
		
		CountryData countryData = entityManager.merge(InfrastructureDataUtil.getDataCountryBelgium());
		
		CityEntity cityEntity = InfrastructureDataUtil.getEntityCityBrussels();
		
		CityData cityData = entityManager.merge(InfrastructureDataUtil.getDataCityBrussels());
		cityData.setCountry(countryData);
		
		idGenerated = cityData.getId();
		when(continentEntityMapper.dataToEntity(any())).thenReturn(cityEntity);
		when(countryJpaRepository.findById(any())).thenReturn(Optional.of(countryData));
	}
	
	@Test
	void testFindAll() {
		CitiesEntity result = repository.findAll(Optional.empty());
		assertThat(result).isNotNull();
		assertThat(result.getItems()).isNotNull();
		assertThat(result.getItems()).hasSize(1);
		CityEntity continentEntity = result.getItems().get(0);
		checkCityContent(continentEntity);
	}
	
	@Test
	void testFindByCountry() {
		CitiesEntity result = repository.findAll(Optional.of(InfrastructureDataUtil.COUNTRY_BELGIUM_ID));
		assertThat(result).isNotNull();
		assertThat(result.getItems()).isNotNull();
		assertThat(result.getItems()).hasSize(1);
		CityEntity continentEntity = result.getItems().get(0);
		checkCityContent(continentEntity);
	}

	
	void checkCityContent(CityEntity toTest) {
		assertThat(toTest.getId()).isEqualTo(InfrastructureDataUtil.CITY_BRUSSELS_ID);
		assertThat(toTest.getName()).isEqualTo(InfrastructureDataUtil.CITY_BRUSSELS_NAME);
		assertThat(toTest.getLatitude()).isEqualTo(InfrastructureDataUtil.CITY_BRUSSELS_LATITUDE);
		assertThat(toTest.getLongitude()).isEqualTo(InfrastructureDataUtil.CITY_BRUSSELS_LONGITUDE);
		assertThat(toTest.getState()).isEqualTo(InfrastructureDataUtil.CITY_BRUSSELS_STATE);
		assertThat(toTest.getCounty()).isEqualTo(InfrastructureDataUtil.CITY_BRUSSELS_COUNTY);
		
		assertThat(toTest.getCountry().getId()).isEqualTo(InfrastructureDataUtil.COUNTRY_BELGIUM_ID);
		assertThat(toTest.getCountry().getName()).isEqualTo(InfrastructureDataUtil.COUNTRY_BELGIUM_NAME);
		assertThat(toTest.getCountry().getCodeiso2()).isEqualTo(InfrastructureDataUtil.COUNTRY_BELGIUM_CODE_ISO2);
		assertThat(toTest.getCountry().getCodeiso3()).isEqualTo(InfrastructureDataUtil.COUNTRY_BELGIUM_CODE_ISO3);
		assertThat(toTest.getCountry().getNumber()).isEqualTo(InfrastructureDataUtil.COUNTRY_BELGIUM_NUMBER);
		
		assertThat(toTest.getCountry().getContinent().getId()).isEqualTo(InfrastructureDataUtil.CONTINENT_EUROPE_ID);
		assertThat(toTest.getCountry().getContinent().getName()).isEqualTo(InfrastructureDataUtil.CONTINENT_EUROPE_NAME);
		assertThat(toTest.getCountry().getContinent().getCode()).isEqualTo(InfrastructureDataUtil.CONTINENT_EUROPE_CODE);
		
		assertThat(toTest.getCountry().getRegion().getId()).isEqualTo(InfrastructureDataUtil.REGION_WESTERN_EUROPE_ID);
		assertThat(toTest.getCountry().getRegion().getName()).isEqualTo(InfrastructureDataUtil.REGION_WESTERN_EUROPE_NAME);
	}
}
