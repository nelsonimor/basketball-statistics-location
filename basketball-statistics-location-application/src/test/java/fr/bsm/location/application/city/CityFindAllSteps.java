package fr.bsm.location.application.city;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import fr.bsm.application.util.ContextHolder;
import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.repository.city.CityRepository;
import fr.bsm.location.domain.repository.city.GeocodingRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CityFindAllSteps {

	private CityRepository cityRepository;

	private CityService cityService;
	
	private GeocodingRepository geocodingRepository;

	private ContextHolder<List<CityEntity>, Optional<CitiesEntity>> holder;

	public CityFindAllSteps() {
		cityRepository = mock(CityRepository.class);
		geocodingRepository = mock(GeocodingRepository.class);
		cityService = new CityServiceImpl(cityRepository,geocodingRepository);
		holder = new ContextHolder<>();
	}
	
	@Given("A list of cities")
	public void a_list_of_cities(List<CityEntity> entities) {
		holder.addParam(entities);
	}
	@When("We get all cities")
	public void we_get_all_cities() {
		when(cityRepository.findAll(Optional.empty()))
		.thenReturn(CitiesEntity.builder().items(holder.getParam()).build());
		holder.addResult(Optional.of(cityService.findAll(Optional.empty())));
	}
	@Then("We return {int} city with id {string} and name {string} and county {string}")
	public void we_return_city_with_id_and_name_and_county(Integer expectedSize, String cityId, String cityName, String cityCounty) {
	    assertThat(holder.getParam()).isNotNull();
	    assertThat(holder.getParam()).hasSize(expectedSize);
	    assertThat(holder.getParam().get(0).getId()).isEqualTo(Integer.parseInt(cityId));
	    assertThat(holder.getParam().get(0).getName()).isEqualTo(cityName);
	    assertThat(holder.getParam().get(0).getCounty()).isEqualTo(cityCounty);
	}




	
}
