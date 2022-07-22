package fr.bsm.location.infrastructure.repository.region;

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

import fr.bsm.location.domain.common.entity.region.RegionEntity;
import fr.bsm.location.domain.common.entity.region.RegionsEntity;
import fr.bsm.location.domain.repository.region.RegionRepository;
import fr.bsm.location.infrastructure.data.region.RegionData;
import fr.bsm.location.infrastructure.util.RegionEntityMapper;

@DataJpaTest
@EntityScan("fr.bsm.location.infrastructure.data")
@EnableAutoConfiguration
@ContextConfiguration(classes = RegionRepositoryImpl.class)
class RegionsRepositoryTest {


	@Autowired
	RegionRepository repository;

	@MockBean
	RegionEntityMapper regionEntityMapper;

	private Integer REGION_BALKAN_ID = 2;
	private String REGION_BALKAN_NAME = "Balkan";



	@BeforeEach
	void dataSetup(@Autowired EntityManager entityManager) {
		entityManager.clear();
		
		
		RegionData balkanData = buildRegionData(REGION_BALKAN_ID, REGION_BALKAN_NAME);
		RegionEntity balkanEntity = buildRegionEntity(REGION_BALKAN_ID, REGION_BALKAN_NAME);
		entityManager.merge(balkanData);
		when(regionEntityMapper.dataToEntity(any())).thenReturn(balkanEntity);
	}
	
	@Test
	void testFindById() {
		Optional<RegionEntity> result = repository.findById(REGION_BALKAN_ID);
		assertThat(result).isPresent();
	}


	@Test
	void testFindAll() {
		RegionsEntity result = repository.findAll();
		assertThat(result).isNotNull();
		assertThat(result.getItems()).isNotNull();
		assertThat(result.getItems()).hasSize(1);
		RegionEntity continentEntity = result.getItems().get(0);
		checkRegionContent(continentEntity);
	}
	


	void checkRegionContent(RegionEntity toTest) {
		assertThat(toTest.getId()).isEqualTo(REGION_BALKAN_ID);
		assertThat(toTest.getName()).isEqualTo(REGION_BALKAN_NAME);
	}


	RegionEntity buildRegionEntity(Integer id,String name) {
		return RegionEntity.builder()
				.id(id)
				.name(name)
				.build();
	}
	
	RegionData buildRegionData(Integer id,String name) {
		return RegionData.builder()
				.id(id)
				.name(name)
				.creationDate(Timestamp.from(Instant.now()))
				.build();
	}

}
