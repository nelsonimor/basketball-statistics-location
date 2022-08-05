package fr.bsm.location.application.city;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import fr.bsm.application.util.ContextHolder;
import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.repository.city.CityRepository;
import fr.bsm.location.domain.repository.city.GeocodingRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CityAddSteps {

	private CityRepository cityRepository;

	private CityService cityService;
	
	private GeocodingRepository geocodingRepository;


	private ContextHolder<List<CityEntity>, Optional<List<CityEntity>>> holder;

	public CityAddSteps() {
		cityRepository = mock(CityRepository.class);
		geocodingRepository = mock(GeocodingRepository.class);
		cityService = new CityServiceImpl(cityRepository,geocodingRepository);
		holder = new ContextHolder<>();
	}

	@Given("A list of one city")
	public void a_list_of_one_city(List<CityEntity> entities) {
		holder.addParam(entities);
	}
	
	@When("We add a city with name {string} and county {string}")
	public void we_add_a_city_with_name_and_county(String cityName, String cityCounty) {
		List<CityEntity> entities = holder.getParam();
		
		CityEntity entityToAdd = new CityEntity();
		entityToAdd.setName(cityName);
		entityToAdd.setCounty(cityCounty);

		when(cityRepository.create(entityToAdd)).thenReturn(entityToAdd);
		when(geocodingRepository.geocode(entityToAdd)).thenReturn(Optional.of(entityToAdd));
	    entities.add(cityService.create(entityToAdd));
	    holder.addResult(Optional.of(entities));
	}
	@Then("We return {string} cities")
	public void we_return_cities(String expectedSize) {
	    assertThat(holder.getParam()).isNotNull();
	    assertThat(holder.getParam()).hasSize(Integer.parseInt(expectedSize));
	}

	
}
