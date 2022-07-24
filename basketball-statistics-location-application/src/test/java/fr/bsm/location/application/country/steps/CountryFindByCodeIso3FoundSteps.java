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

public class CountryFindByCodeIso3FoundSteps {

	private CountryRepository countryRepository;

	private CountryService countryService;

	private ContextHolder<List<CountryEntity>, Optional<CountryEntity>> holder;

	public CountryFindByCodeIso3FoundSteps() {
		countryRepository = mock(CountryRepository.class);
		countryService = new CountryServiceImpl(countryRepository);
		holder = new ContextHolder<>();
	}

	
	@Given("A collection of countries with different codeIso3")
	public void a_collection_of_countries_with_different_code_iso3(List<CountryEntity> entities) {
		holder.addParam(entities);
	}
	@When("I get country with the code {string}")
	public void i_get_country_with_the_code(String codeiso3) {
		when(countryRepository.findByCodeiso3(codeiso3)).thenReturn(
				holder.getParam().stream().filter(e -> e.getCodeiso3().equals(codeiso3))
				.findFirst());
		holder.addResult(countryService.findByCodeiso3(codeiso3));
	}
	@Then("I return country for {string} with {string} and {string} and {string}")
	public void i_return_country_for_with_and_and(String id, String name, String number, String codeiso3) {
	    assertThat(holder.getResult()).isPresent();
	    CountryEntity entity = holder.getResult().get();
	    assertThat(entity.getId()).isEqualTo(Integer.parseInt(id));
	    assertThat(entity.getName()).isEqualTo(name);
	    assertThat(entity.getNumber()).isEqualTo(number);
	    assertThat(entity.getCodeiso3()).isEqualTo(codeiso3);
	}
	

	

	









}
