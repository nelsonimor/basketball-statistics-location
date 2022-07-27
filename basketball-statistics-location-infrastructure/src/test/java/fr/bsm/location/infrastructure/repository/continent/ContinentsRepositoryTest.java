package fr.bsm.location.infrastructure.repository.continent;

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

import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.continent.ContinentsEntity;
import fr.bsm.location.domain.repository.continent.ContinentRepository;
import fr.bsm.location.infrastructure.data.continent.ContinentData;
import fr.bsm.location.infrastructure.util.ContinentEntityMapper;
import fr.bsm.location.infrastructure.util.InfrastructureDataUtil;

@DataJpaTest
@EntityScan("fr.bsm.location.infrastructure.data")
@EnableAutoConfiguration
@ContextConfiguration(classes = ContinentRepositoryImpl.class)
class ContinentsRepositoryTest {


	@Autowired
	ContinentRepository repository;

	@MockBean
	ContinentEntityMapper continentEntityMapper;
	
	Integer idGenerated = 0;


	@BeforeEach
	void dataSetup(@Autowired EntityManager entityManager) {
		entityManager.clear();
		ContinentEntity continentEuropeEntity = InfrastructureDataUtil.getEntityContinentEurope();
		ContinentData continentEuropeData = entityManager.merge(InfrastructureDataUtil.getDataContinentEurope());
		idGenerated = continentEuropeData.getId();
		when(continentEntityMapper.dataToEntity(any())).thenReturn(continentEuropeEntity);
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
	
	@Test
	void testFindById() {
		Optional<ContinentEntity> result = repository.findById(idGenerated);
		assertThat(result).isPresent();
	}
	


	void checkContinentContent(ContinentEntity toTest) {
		assertThat(toTest.getId()).isEqualTo(InfrastructureDataUtil.CONTINENT_EUROPE_ID);
		assertThat(toTest.getName()).isEqualTo(InfrastructureDataUtil.CONTINENT_EUROPE_NAME);
		assertThat(toTest.getCode()).isEqualTo(InfrastructureDataUtil.CONTINENT_EUROPE_CODE);
	}


}
