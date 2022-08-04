package fr.bsm.location.application.city;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.Optional;

import fr.bsm.application.util.ContextHolder;
import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.repository.city.CityRepository;
import fr.bsm.location.domain.repository.city.GeocodingRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CityDeleteSteps {

	private CityRepository cityRepository;

	private CityService cityService;
	
	private GeocodingRepository geocodingRepository;


	private ContextHolder<List<CityEntity>, Optional<List<CityEntity>>> holder;

	public CityDeleteSteps() {
		cityRepository = mock(CityRepository.class);
		geocodingRepository = mock(GeocodingRepository.class);
		cityService = new CityServiceImpl(cityRepository,geocodingRepository);
		holder = new ContextHolder<>();
	}

	
	@Given("A list of two city")
	public void a_list_of_two_city(List<CityEntity> entities) {
		holder.addParam(entities);
	}
	@When("We remove a city with id {string}")
	public void we_remove_a_city_with_id(String idCity) {
		List<CityEntity> entities = holder.getParam();
		
		CityEntity entityToRemove= new CityEntity();
		entityToRemove.setId(Integer.parseInt(idCity));

		doNothing().when(cityRepository).delete(Integer.parseInt(idCity));
		
		cityService.delete(Integer.parseInt(idCity));
		
	    entities.remove(entityToRemove);
	    holder.addResult(Optional.of(entities));
	}
	@Then("We return only {string} city")
	public void we_return_only_city(String expectedSize) {
	    assertThat(holder.getParam()).isNotNull();
	    assertThat(holder.getParam()).hasSize(Integer.parseInt(expectedSize));
	}

	
}
