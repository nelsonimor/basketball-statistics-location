package fr.bsm.location.application.city;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.eq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.bsm.application.util.ApplicationDataUtil;
import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.common.exception.GeocodingException;
import fr.bsm.location.domain.repository.city.CityRepository;
import fr.bsm.location.domain.repository.city.GeocodingRepository;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

	@Mock
	CityRepository cityRepository;
	
	@Mock
	GeocodingRepository geocodingRepository;

	CityService cityService;


	@BeforeEach
	void setUp() {
		cityService = new CityServiceImpl(cityRepository,geocodingRepository);
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
	
	@Test
	void testAddCitySuccess() {
		when(geocodingRepository.geocode(any())).thenReturn(Optional.of(ApplicationDataUtil.getCityBrussels()));
		when(cityRepository.create(any())).thenReturn(ApplicationDataUtil.getCityBrussels());
		CityEntity result = cityService.create(ApplicationDataUtil.getCityBrusselsWithoutGeocoding());;
		assertThat(result).isNotNull();
	}
	
	@Test
	void testAddCityGeocodingKo() {
		when(geocodingRepository.geocode(any())).thenReturn(Optional.empty());
		assertThatThrownBy(() -> cityService.create(ApplicationDataUtil.getCityBrusselsWithoutGeocoding())).isInstanceOf(GeocodingException.class);
	}
	
	
	
	@Test
	void testFindById() {
		when(cityRepository.findById(ApplicationDataUtil.CITY_BRUSSELS_ID)).thenReturn(Optional.of(ApplicationDataUtil.getCityBrussels()));
		Optional<CityEntity> result = cityService.findById(ApplicationDataUtil.CITY_BRUSSELS_ID);
		assertThat(result).isNotNull();
		assertThat(result.get()).isNotNull();
		assertThat(result.get().getId()).isEqualTo(ApplicationDataUtil.CITY_BRUSSELS_ID);
	}
	
	@Test
	void testDeleteById() {
		doNothing().when(cityRepository).delete(ApplicationDataUtil.CITY_BRUSSELS_ID);
		cityService.delete(ApplicationDataUtil.CITY_BRUSSELS_ID);
		verify(cityRepository).delete(ApplicationDataUtil.CITY_BRUSSELS_ID);
	}
	
	@Test
	void testFindByNameAndCountry() {
		when(cityRepository.findByNameAndCountry(ApplicationDataUtil.CITY_BRUSSELS_NAME,ApplicationDataUtil.getCountryBelgium())).thenReturn(Optional.of(ApplicationDataUtil.getCityBrussels()));
		Optional<CityEntity> result = cityService.findByNameAndCountry(ApplicationDataUtil.CITY_BRUSSELS_NAME,ApplicationDataUtil.getCountryBelgium());
		assertThat(result).isNotNull();
		assertThat(result.get()).isNotNull();
		assertThat(result.get().getId()).isEqualTo(ApplicationDataUtil.CITY_BRUSSELS_ID);
	}
	



}
