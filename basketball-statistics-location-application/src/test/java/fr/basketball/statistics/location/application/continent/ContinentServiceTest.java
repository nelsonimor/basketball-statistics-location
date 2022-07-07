package fr.basketball.statistics.location.application.continent;

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

import fr.basketball.statistics.location.domain.common.entity.continent.ContinentEntity;
import fr.basketball.statistics.location.domain.common.entity.continent.ContinentsEntity;
import fr.basketball.statistics.location.domain.repository.continent.ContinentRepository;

@ExtendWith(MockitoExtension.class)
class ContinentServiceTest {

	@Mock
	ContinentRepository continentRepository;

	ContinentService continentService;

	private Integer CONTINENT_AFRICA_ID = 1;
	private String CONTINENT_AFRICA_NAME = "Africa";
	private String CONTINENT_AFRICA_CODE = "AF";

	private Integer CONTINENT_EUROPE_ID = 2;
	private String CONTINENT_EUROPE_NAME = "Europe";
	private String CONTINENT_EUROPE_CODE = "EU";

	@BeforeEach
	void setUp() {
		continentService = new ContinentServiceImpl(continentRepository);
	}

	@Test
	void testFindAll() {
		List<ContinentEntity> entities = createEntityItems();
		ContinentsEntity entityToReturn = ContinentsEntity.builder().items(entities).build();
		when(continentRepository.findAll()).thenReturn(entityToReturn);
		ContinentsEntity result = continentService.findAll();
		assertThat(result.getItems()).hasSize(2);
		verify(continentRepository).findAll();
	}
	
	@Test
	void testFindById() {
		when(continentRepository.findById(CONTINENT_EUROPE_ID)).thenReturn(Optional.of(createContinentEntity(CONTINENT_EUROPE_ID,CONTINENT_EUROPE_NAME,CONTINENT_EUROPE_CODE)));
		Optional<ContinentEntity> result = continentService.findById(CONTINENT_EUROPE_ID);
		assertThat(result).isNotNull();
		assertThat(result.get()).isNotNull();
		assertThat(result.get().getId()).isEqualTo(CONTINENT_EUROPE_ID);
	}


	private List<ContinentEntity> createEntityItems() {
		return Arrays.asList(createContinentEntity(CONTINENT_AFRICA_ID,CONTINENT_AFRICA_NAME,CONTINENT_AFRICA_CODE), createContinentEntity(CONTINENT_EUROPE_ID,CONTINENT_EUROPE_NAME,CONTINENT_EUROPE_CODE));
	}
	
	private ContinentEntity createContinentEntity(Integer id,String name,String code) {
		return ContinentEntity.builder()
				.id(id)
				.name(name)
				.code(code)
				.build();
	}


}
