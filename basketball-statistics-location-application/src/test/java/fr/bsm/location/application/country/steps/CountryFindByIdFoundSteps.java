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

public class CountryFindByIdFoundSteps {

	private CountryRepository countryRepository;

	private CountryService countryService;

	private ContextHolder<List<CountryEntity>, Optional<CountryEntity>> holder;

	public CountryFindByIdFoundSteps() {
		countryRepository = mock(CountryRepository.class);
		countryService = new CountryServiceImpl(countryRepository);
		holder = new ContextHolder<>();
	}

	@Given("A collection of countries with different id")
	public void a_collection_of_countries_with_different_id(List<CountryEntity> entities) {
		holder.addParam(entities);
	}
	@When("I get country with {string}")
	public void i_get_country_with(String id) {
		when(countryRepository.findById(Integer.parseInt(id))).thenReturn(
				holder.getParam().stream().filter(e -> e.getId() == Integer.parseInt(id))
				.findFirst());
		holder.addResult(countryService.findById(Integer.parseInt(id)));
	}
	@Then("I return a result for {string} with {string} and {string}")
	public void i_return_a_result_for_with_and(String id, String name, String codeiso3) {
			    assertThat(holder.getResult()).isPresent();
			    CountryEntity entity = holder.getResult().get();
			    assertThat(entity.getId()).isEqualTo(Integer.parseInt(id));
			    assertThat(entity.getName()).isEqualTo(name);
			    assertThat(entity.getCodeiso3()).isEqualTo(codeiso3);
	}








}
