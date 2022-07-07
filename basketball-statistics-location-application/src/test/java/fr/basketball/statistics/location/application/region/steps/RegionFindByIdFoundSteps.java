package fr.basketball.statistics.location.application.region.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import fr.basketball.statistics.location.application.region.RegionService;
import fr.basketball.statistics.location.application.region.RegionServiceImpl;
import fr.basketball.statistics.location.application.region.context.RegionContextHolder;
import fr.basketball.statistics.location.domain.common.entity.region.RegionEntity;
import fr.basketball.statistics.location.domain.repository.region.RegionRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegionFindByIdFoundSteps {

	private RegionRepository regionRepository;

	private RegionService regionService;

	private RegionContextHolder<List<RegionEntity>, Optional<RegionEntity>> holder;

	public RegionFindByIdFoundSteps() {
		regionRepository = mock(RegionRepository.class);
		regionService = new RegionServiceImpl(regionRepository);
		holder = new RegionContextHolder<>();
	}

	
	@Given("A collection of regions with different id")
	public void a_collection_of_regions_with_different_id(List<RegionEntity> entities) {
		holder.addParam(entities);
	}
	
	
	@When("I get region with {string}")
	public void i_get_region_with(String id) {
		when(regionRepository.findById(Integer.parseInt(id))).thenReturn(
				holder.getParam().stream().filter(e -> e.getId() == Integer.parseInt(id))
				.findFirst());
		holder.addResult(regionService.findById(Integer.parseInt(id)));
	}
	@Then("I return a result for {string} with {string}")
	public void i_return_a_result_for_with_and(String id, String name) {
			    assertThat(holder.getResult()).isPresent();
			    RegionEntity entity = holder.getResult().get();
			    assertThat(entity.getId()).isEqualTo(Integer.parseInt(id));
			    assertThat(entity.getName()).isEqualTo(name);
	}








}
