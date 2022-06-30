package fr.basketball.statistics.location.application.continent.steps;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import fr.basketball.statistics.location.application.continent.ContinentService;
import fr.basketball.statistics.location.application.continent.ContinentServiceImpl;
import fr.basketball.statistics.location.application.continent.context.ContinentContextHolder;
import fr.basketball.statistics.location.domain.common.entity.ContinentEntity;
import fr.basketball.statistics.location.domain.repository.ContinentRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContinentFindByIdFoundSteps {

	private ContinentRepository continentRepository;

	private ContinentService continentService;

	private ContinentContextHolder<List<ContinentEntity>, Optional<ContinentEntity>> holder;

	public ContinentFindByIdFoundSteps() {
		continentRepository = mock(ContinentRepository.class);
		continentService = new ContinentServiceImpl(continentRepository);
		holder = new ContinentContextHolder<>();
	}

	@Given("A collection of continents with different id")
	public void a_collection_of_continents_with_different_id(List<ContinentEntity> entities) {
		holder.addParam(entities);
	}
	@When("I get continent with {string}")
	public void i_get_continent_with(String id) {
		when(continentRepository.findById(Integer.parseInt(id))).thenReturn(
				holder.getParam().stream().filter(e -> e.getId() == Integer.parseInt(id))
				.findFirst());
		holder.addResult(continentService.findById(Integer.parseInt(id)));
	}
	@Then("I return a result for {string} with {string} and {string}")
	public void i_return_a_result_for_with_and(String id, String name, String code) {
			    assertThat(holder.getResult()).isPresent();
			    ContinentEntity entity = holder.getResult().get();
			    assertThat(entity.getId()).isEqualTo(Integer.parseInt(id));
			    assertThat(entity.getName()).isEqualTo(name);
			    assertThat(entity.getCode()).isEqualTo(code);
	}








}
