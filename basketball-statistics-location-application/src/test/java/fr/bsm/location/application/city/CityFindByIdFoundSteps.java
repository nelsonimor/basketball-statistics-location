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

public class CityFindByIdFoundSteps {

	private CityRepository cityRepository;

	private GeocodingRepository geocodingRepository;

	private CityService cityService;

	private ContextHolder<List<CityEntity>, Optional<CityEntity>> holder;

	public CityFindByIdFoundSteps() {
		cityRepository = mock(CityRepository.class);
		geocodingRepository = mock(GeocodingRepository.class);
		cityService = new CityServiceImpl(cityRepository,geocodingRepository);
		holder = new ContextHolder<>();
	}

	@Given("A collection of cities with different id")
	public void a_collection_of_cities_with_different_id(List<CityEntity> entities) {
		holder.addParam(entities);
	}
	@When("I get city with {string}")
	public void i_get_city_with(String id) {
		when(cityRepository.findById(Integer.parseInt(id))).thenReturn(
				holder.getParam().stream().filter(e -> e.getId() == Integer.parseInt(id))
				.findFirst());
		holder.addResult(cityService.findById(Integer.parseInt(id)));
	}
	@Then("I return a result for {string} with {string}")
	public void i_return_a_result_for_with(String cityId, String cityName) {
		assertThat(holder.getResult()).isPresent();
		CityEntity entity = holder.getResult().get();
		assertThat(entity.getId()).isEqualTo(Integer.parseInt(cityId));
		assertThat(entity.getName()).isEqualTo(cityName);
	}







}
