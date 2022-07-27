package fr.bsm.location.application.cities;

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
import fr.bsm.location.application.city.CityService;
import fr.bsm.location.application.city.CityServiceImpl;
import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.repository.city.CityRepository;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

	@Mock
	CityRepository cityRepository;

	CityService cityService;


	@BeforeEach
	void setUp() {
		cityService = new CityServiceImpl(cityRepository);
	}

	@Test
	void testFindAll() {
		List<CityEntity> entities = Arrays.asList(ApplicationDataUtil.getCityBrussels());
		CitiesEntity entityToReturn = CitiesEntity.builder().items(entities).build();
		when(cityRepository.findAll(Optional.empty())).thenReturn(entityToReturn);
		CitiesEntity result = cityService.findAll(Optional.empty());
		assertThat(result.getItems()).hasSize(1);
		verify(cityRepository).findAll(Optional.empty());
	}
	
	@Test
	void testFindByCountry() {
		List<CityEntity> entities = Arrays.asList(ApplicationDataUtil.getCityBrussels());
		CitiesEntity entityToReturn = CitiesEntity.builder().items(entities).build();
		when(cityRepository.findAll(Optional.of(ApplicationDataUtil.COUNTRY_BELGIUM_ID))).thenReturn(entityToReturn);
		CitiesEntity result = cityService.findAll(Optional.of(ApplicationDataUtil.COUNTRY_BELGIUM_ID));
		assertThat(result.getItems()).hasSize(1);
		verify(cityRepository).findAll(Optional.of(ApplicationDataUtil.COUNTRY_BELGIUM_ID));
	}


}
