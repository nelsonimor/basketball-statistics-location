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

public class CountryFindByCodeIso2FoundSteps {

	private CountryRepository countryRepository;

	private CountryService countryService;

	private ContextHolder<List<CountryEntity>, Optional<CountryEntity>> holder;

	public CountryFindByCodeIso2FoundSteps() {
		countryRepository = mock(CountryRepository.class);
		countryService = new CountryServiceImpl(countryRepository);
		holder = new ContextHolder<>();
	}

	
	@Given("A collection of countries with different codeIso2")
	public void a_collection_of_countries_with_different_code_iso2(List<CountryEntity> entities) {
		holder.addParam(entities);
	}
	
	@When("I get a country with the code {string}")
	public void i_get_a_country_with_the_code(String codeiso2) {
		when(countryRepository.findByCodeiso2(codeiso2)).thenReturn(
				holder.getParam().stream().filter(e -> e.getCodeiso2().equals(codeiso2))
				.findFirst());
		holder.addResult(countryService.findByCodeiso2(codeiso2));
	}
	
	@Then("I return a country for {string} with {string} and {string} and {string}")
	public void i_return_a_country_for_with_and_and(String id, String name, String codeiso2, String codeiso3) {
	    assertThat(holder.getResult()).isPresent();
	    CountryEntity entity = holder.getResult().get();
	    assertThat(entity.getId()).isEqualTo(Integer.parseInt(id));
	    assertThat(entity.getName()).isEqualTo(name);
	    assertThat(entity.getCodeiso2()).isEqualTo(codeiso2);
	    assertThat(entity.getCodeiso3()).isEqualTo(codeiso3);
	}








}
