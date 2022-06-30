package fr.basketball.statistics.location.infrastructure.repository.continent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.Instant;
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

import fr.basketball.statistics.location.domain.common.entity.ContinentEntity;
import fr.basketball.statistics.location.domain.common.entity.ContinentsEntity;
import fr.basketball.statistics.location.domain.repository.ContinentRepository;
import fr.basketball.statistics.location.infrastructure.data.ContinentData;
import fr.basketball.statistics.location.infrastructure.util.ContinentEntityMapper;

@DataJpaTest
@EntityScan("fr.basketball.statistics.location.infrastructure.data")
@EnableAutoConfiguration
@ContextConfiguration(classes = ContinentRepositoryImpl.class)
class ContinentsRepositoryTest {


	@Autowired
	ContinentRepository repository;

	@MockBean
	ContinentEntityMapper continentEntityMapper;

	private Integer CONTINENT_AFRICA_ID = 2;
	private String CONTINENT_AFRICA_NAME = "Africa";
	private String CONTINENT_AFRICA_CODE = "AF";
	


	@BeforeEach
	void dataSetup(@Autowired EntityManager entityManager) {
		entityManager.clear();
		
		
		ContinentData africaData = buildContinentData(CONTINENT_AFRICA_ID, CONTINENT_AFRICA_NAME, CONTINENT_AFRICA_CODE);
		ContinentEntity africaEntity = buildContinentEntity(CONTINENT_AFRICA_ID, CONTINENT_AFRICA_NAME, CONTINENT_AFRICA_CODE);
		entityManager.merge(africaData);
		when(continentEntityMapper.dataToEntity(any())).thenReturn(africaEntity);
	}
	
	@Test
	void testFindById() {
		Optional<ContinentEntity> result = repository.findById(CONTINENT_AFRICA_ID);
		assertThat(result).isPresent();
	}


	@Test
	void testFindAll() {
		ContinentsEntity result = repository.findAll();
		assertThat(result).isNotNull();
		assertThat(result.getItems()).isNotNull();
		assertThat(result.getItems()).hasSize(1);
		ContinentEntity continentEntity = result.getItems().get(0);
		checkContinentContent(continentEntity);
	}
	


	void checkContinentContent(ContinentEntity toTest) {
		assertThat(toTest.getId()).isEqualTo(CONTINENT_AFRICA_ID);
		assertThat(toTest.getName()).isEqualTo(CONTINENT_AFRICA_NAME);
		assertThat(toTest.getCode()).isEqualTo(CONTINENT_AFRICA_CODE);
	}


	ContinentEntity buildContinentEntity(Integer id,String name,String code) {
		return ContinentEntity.builder()
				.id(id)
				.name(name)
				.code(code)
				.build();
	}
	
	ContinentData buildContinentData(Integer id,String name,String code) {
		return ContinentData.builder()
				.id(id)
				.name(name)
				.code(code)
				.creationDate(Timestamp.from(Instant.now()))
				.build();
	}

}
