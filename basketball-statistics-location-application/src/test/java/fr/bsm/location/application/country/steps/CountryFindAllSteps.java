package fr.bsm.location.application.country.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import fr.bsm.application.util.ContextHolder;
import fr.bsm.location.application.country.CountryService;
import fr.bsm.location.application.country.CountryServiceImpl;
import fr.bsm.location.domain.common.entity.country.CountriesEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.repository.country.CountryRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CountryFindAllSteps {

	private CountryRepository countryRepository;

	private CountryService countryService;

	private ContextHolder<List<CountryEntity>, Optional<CountriesEntity>> holder;

	public CountryFindAllSteps() {
		countryRepository = mock(CountryRepository.class);
		countryService = new CountryServiceImpl(countryRepository);
		holder = new ContextHolder<>();
	}


	@Given("A list of countries")
	public void a_list_of_countries(List<CountryEntity> entities) {
		holder.addParam(entities);
	}
	@When("We get all countries")
	public void we_get_all_countries() {
		when(countryService.findAll(Optional.empty(),Optional.empty()))
		.thenReturn(CountriesEntity.builder().items(holder.getParam()).build());
		holder.addResult(Optional.of(countryService.findAll(Optional.empty(),Optional.empty())));
	}
	@Then("We return {int} countries with id {string},{string}, name {string},{string}")
	public void we_return_countries_with_id_name_and_code(Integer expectedSize, String id1, String id2, String name1, String name2) {
	    assertThat(holder.getParam()).isNotNull();
	    assertThat(holder.getParam()).hasSize(expectedSize);
	    assertThat(holder.getParam().get(0).getId()).isEqualTo(Integer.parseInt(id1));
	    assertThat(holder.getParam().get(1).getId()).isEqualTo(Integer.parseInt(id2));
	    assertThat(holder.getParam().get(0).getName()).isEqualTo(name1);
	    assertThat(holder.getParam().get(1).getName()).isEqualTo(name2);
	}
	
}
