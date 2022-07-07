package fr.basketball.statistics.location.application.continent.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import fr.basketball.statistics.location.application.continent.ContinentService;
import fr.basketball.statistics.location.application.continent.ContinentServiceImpl;
import fr.basketball.statistics.location.application.continent.context.ContinentContextHolder;
import fr.basketball.statistics.location.domain.common.entity.continent.ContinentEntity;
import fr.basketball.statistics.location.domain.common.entity.continent.ContinentsEntity;
import fr.basketball.statistics.location.domain.repository.continent.ContinentRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContinentFindAllSteps {

	private ContinentRepository continentRepository;

	private ContinentService continentService;

	private ContinentContextHolder<List<ContinentEntity>, Optional<ContinentsEntity>> holder;

	public ContinentFindAllSteps() {
		continentRepository = mock(ContinentRepository.class);
		continentService = new ContinentServiceImpl(continentRepository);
		holder = new ContinentContextHolder<>();
	}


	@Given("A list of sales continents")
	public void a_list_of_sales_continents(List<ContinentEntity> entities) {
		holder.addParam(entities);
	}
	@When("We get all continents")
	public void we_get_all_continents() {
		when(continentService.findAll())
		.thenReturn(ContinentsEntity.builder().items(holder.getParam()).build());
		holder.addResult(Optional.of(continentService.findAll()));
	}
	@Then("We return {int} continents with id {string},{string}, name {string},{string} and code {string}, {string}")
	public void we_return_continents_with_id_name_and_code(Integer expectedSize, String id1, String id2, String name1, String name2, String code1, String code2) {
	    assertThat(holder.getParam()).isNotNull();
	    assertThat(holder.getParam()).hasSize(expectedSize);
	    assertThat(holder.getParam().get(0).getId()).isEqualTo(Integer.parseInt(id1));
	    assertThat(holder.getParam().get(1).getId()).isEqualTo(Integer.parseInt(id2));
	    assertThat(holder.getParam().get(0).getName()).isEqualTo(name1);
	    assertThat(holder.getParam().get(1).getName()).isEqualTo(name2);
	    assertThat(holder.getParam().get(0).getCode()).isEqualTo(code1);
	    assertThat(holder.getParam().get(1).getCode()).isEqualTo(code2);
	}



}
