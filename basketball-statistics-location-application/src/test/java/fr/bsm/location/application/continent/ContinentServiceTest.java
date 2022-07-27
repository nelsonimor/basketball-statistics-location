package fr.bsm.location.application.continent;

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
import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.continent.ContinentsEntity;
import fr.bsm.location.domain.repository.continent.ContinentRepository;

@ExtendWith(MockitoExtension.class)
class ContinentServiceTest {

	@Mock
	ContinentRepository continentRepository;

	ContinentService continentService;

	@BeforeEach
	void setUp() {
		continentService = new ContinentServiceImpl(continentRepository);
	}

	@Test
	void testFindAll() {
		List<ContinentEntity> entities =  Arrays.asList(ApplicationDataUtil.getContinentEurope());
		ContinentsEntity entityToReturn = ContinentsEntity.builder().items(entities).build();
		when(continentRepository.findAll()).thenReturn(entityToReturn);
		ContinentsEntity result = continentService.findAll();
		assertThat(result.getItems()).hasSize(1);
		verify(continentRepository).findAll();
	}
	
	@Test
	void testFindById() {
		when(continentRepository.findById(ApplicationDataUtil.CONTINENT_EUROPE_ID)).thenReturn(Optional.of(ApplicationDataUtil.getContinentEurope()));
		Optional<ContinentEntity> result = continentService.findById(ApplicationDataUtil.CONTINENT_EUROPE_ID);
		assertThat(result).isNotNull();
		assertThat(result.get()).isNotNull();
		assertThat(result.get().getId()).isEqualTo(ApplicationDataUtil.CONTINENT_EUROPE_ID);
	}

}
