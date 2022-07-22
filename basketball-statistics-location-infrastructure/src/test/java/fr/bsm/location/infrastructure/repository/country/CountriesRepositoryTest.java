package fr.bsm.location.infrastructure.repository.country;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.Instant;
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

import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.country.CountriesEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.common.entity.region.RegionEntity;
import fr.bsm.location.domain.repository.country.CountryRepository;
import fr.bsm.location.infrastructure.data.continent.ContinentData;
import fr.bsm.location.infrastructure.data.country.CountryData;
import fr.bsm.location.infrastructure.data.region.RegionData;
import fr.bsm.location.infrastructure.repository.continent.ContinentJpaRepository;
import fr.bsm.location.infrastructure.repository.region.RegionJpaRepository;
import fr.bsm.location.infrastructure.util.CountryEntityMapper;

@DataJpaTest
@EntityScan("fr.bsm.location.infrastructure.data")
@EnableAutoConfiguration
@ContextConfiguration(classes = CountryRepositoryImpl.class)
@TestMethodOrder(OrderAnnotation.class)
class CountriesRepositoryTest {


	@Autowired
	CountryRepository repository;

	@MockBean
	CountryEntityMapper countryEntityMapper;
	
	@MockBean
	ContinentJpaRepository continentJpaRepository;
	
	@MockBean
	RegionJpaRepository regionJpaRepository;

	private Integer BELGIUM_ID = 1;
	private String BELGIUM_NAME = "Belgium";
	private String BELGIUM_CODE_ISO2 = "BE";
	private String BELGIUM_CODE_ISO3 = "BEL";
	private String BELGIUM_FULL_NAME = "Belgium Kingdom";
	private String BELGIUM_NUMBER = "123";
	
	private Integer EUROPE_ID = 4;
	private String EUROPE_NAME = "Europe";
	private String EUROPE_CODE = "EU";
	
	private Integer WESTERN_EUROPE_ID = 6;
	private String WESTERN_EUROPE_NAME = "Western Europe";



	@BeforeEach
	void dataSetup(@Autowired EntityManager entityManager) {
		entityManager.clear();

		
		RegionEntity regionEntity = RegionEntity.builder().name(WESTERN_EUROPE_NAME).id(WESTERN_EUROPE_ID).build();
		ContinentEntity continentEntity = ContinentEntity.builder().name(EUROPE_NAME).id(EUROPE_ID).code(EUROPE_CODE).build();
		
		
		CountryEntity belgiumEntity = buildCountryEntity(BELGIUM_ID, BELGIUM_NAME, BELGIUM_CODE_ISO2,BELGIUM_CODE_ISO3,BELGIUM_FULL_NAME,BELGIUM_NUMBER,regionEntity,continentEntity);

		
		RegionData regionData = entityManager.merge(RegionData.builder().name(WESTERN_EUROPE_NAME).id(WESTERN_EUROPE_ID).creationDate(Timestamp.from(Instant.now())).build());
		
		

		ContinentData continentData = entityManager.merge(ContinentData.builder().name(EUROPE_NAME).id(EUROPE_ID).code(EUROPE_CODE).creationDate(Timestamp.from(Instant.now())).build());
		
		
		CountryData belgiumData = buildCountryData(BELGIUM_ID, BELGIUM_NAME, BELGIUM_CODE_ISO2,BELGIUM_CODE_ISO3,BELGIUM_NUMBER,BELGIUM_FULL_NAME,regionData,continentData);
		belgiumData.setRegion(regionData);
		belgiumData.setContinent(continentData);
		
		
		belgiumData = entityManager.merge(belgiumData);
		
		when(regionJpaRepository.findById(any())).thenReturn(Optional.of(regionData));
		when(continentJpaRepository.findById(any())).thenReturn(Optional.of(continentData));
		when(countryEntityMapper.dataToEntity(any())).thenReturn(belgiumEntity);
	}
	
	@Test
	@Order(1)
	void testFindById() {
		Optional<CountryEntity> result = repository.findById(BELGIUM_ID);
		assertThat(result).isPresent();
		checkCountryContent(result.get());
	}
	
	@Test
	void testFindByName() {
		Optional<CountryEntity> result = repository.findByName(BELGIUM_NAME);
		assertThat(result).isPresent();
		checkCountryContent(result.get());
	}
	
	@Test
	void testFindByCodeiso2() {
		Optional<CountryEntity> result = repository.findByCodeiso2(BELGIUM_CODE_ISO2);
		assertThat(result).isPresent();
		checkCountryContent(result.get());
	}
	
	@Test
	void testFindByCodeiso3() {
		Optional<CountryEntity> result = repository.findByCodeiso3(BELGIUM_CODE_ISO3);
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
		CountriesEntity result = repository.findAll(Optional.of(EUROPE_ID),Optional.empty());
		assertThat(result).isNotNull();
		assertThat(result.getItems()).isNotNull();
		assertThat(result.getItems()).hasSize(1);
		CountryEntity countryEntity = result.getItems().get(0);
		checkCountryContent(countryEntity);
	}

	@Test
	void testFindByRegion() {
		CountriesEntity result = repository.findAll(Optional.empty(),Optional.of(WESTERN_EUROPE_ID));
		assertThat(result).isNotNull();
		assertThat(result.getItems()).isNotNull();
		assertThat(result.getItems()).hasSize(1);
		CountryEntity countryEntity = result.getItems().get(0);
		checkCountryContent(countryEntity);
	}
	
	@Test
	void testFindByContinentAndRegion() {
		CountriesEntity result = repository.findAll(Optional.of(EUROPE_ID),Optional.of(WESTERN_EUROPE_ID));
		assertThat(result).isNotNull();
		assertThat(result.getItems()).isNotNull();
		assertThat(result.getItems()).hasSize(1);
		CountryEntity countryEntity = result.getItems().get(0);
		checkCountryContent(countryEntity);
	}

	void checkCountryContent(CountryEntity toTest) {
		assertThat(toTest.getId()).isEqualTo(BELGIUM_ID);
		assertThat(toTest.getName()).isEqualTo(BELGIUM_NAME);
		assertThat(toTest.getCodeiso2()).isEqualTo(BELGIUM_CODE_ISO2);
		assertThat(toTest.getCodeiso3()).isEqualTo(BELGIUM_CODE_ISO3);
		assertThat(toTest.getNumber()).isEqualTo(BELGIUM_NUMBER);
		
		assertThat(toTest.getContinent().getId()).isEqualTo(EUROPE_ID);
		assertThat(toTest.getContinent().getName()).isEqualTo(EUROPE_NAME);
		assertThat(toTest.getContinent().getCode()).isEqualTo(EUROPE_CODE);
		
		assertThat(toTest.getRegion().getId()).isEqualTo(WESTERN_EUROPE_ID);
		assertThat(toTest.getRegion().getName()).isEqualTo(WESTERN_EUROPE_NAME);
	}


	CountryEntity buildCountryEntity(Integer id,String name,String codeiso2,String codeiso3,String fullname,String number,RegionEntity region,ContinentEntity continent) {
		return CountryEntity.builder()
				.id(id)
				.name(name)
				.codeiso2(codeiso2)
				.codeiso3(codeiso3)
				.fullname(fullname)
				.number(number)
				.region(region)
				.continent(continent)
				.build();
	}
	
	CountryData buildCountryData(Integer id,String name,String codeiso2,String codeiso3,String number,String fullname,RegionData region,ContinentData continent) {
		return CountryData.builder()
				.id(id)
				.name(name)
				.codeiso2(codeiso2)
				.codeiso3(codeiso3)
				.fullname(fullname)
				.continent(continent)
				.number(number)
				.region(region)
				.creationDate(Timestamp.from(Instant.now()))
				.build();
	}

}
