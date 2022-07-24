package fr.bsm.location.application.country.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import fr.bsm.application.util.ContextHolder;
import fr.bsm.location.application.country.CountryService;
import fr.bsm.location.application.country.CountryServiceImpl;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.repository.country.CountryRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CountryFindByNameFoundSteps {

	private CountryRepository countryRepository;

	private CountryService countryService;

	private ContextHolder<List<CountryEntity>, Optional<CountryEntity>> holder;

	public CountryFindByNameFoundSteps() {
		countryRepository = mock(CountryRepository.class);
		countryService = new CountryServiceImpl(countryRepository);
		holder = new ContextHolder<>();
	}


	@Given("A collection of countries with different name")
	public void a_collection_of_countries_with_different_name(List<CountryEntity> entities) {
		holder.addParam(entities);
	}
	
	@When("I get a country with {string}")
	public void i_get_a_country_with(String name) {
		when(countryRepository.findByName(name)).thenReturn(
				holder.getParam().stream().filter(e -> e.getName().equals(name))
				.findFirst());
		holder.addResult(countryService.findByName(name));
	}
	@Then("I return country for {string} with {string} and {string}")
	public void i_return_country_for_with_and(String id, String name, String codeiso2) {
	    assertThat(holder.getResult()).isPresent();
	    CountryEntity entity = holder.getResult().get();
	    assertThat(entity.getId()).isEqualTo(Integer.parseInt(id));
	    assertThat(entity.getName()).isEqualTo(name);
	    assertThat(entity.getCodeiso2()).isEqualTo(codeiso2);
	}








}
