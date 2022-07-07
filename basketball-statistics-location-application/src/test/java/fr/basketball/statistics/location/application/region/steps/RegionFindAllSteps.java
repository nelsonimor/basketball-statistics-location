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
import fr.basketball.statistics.location.domain.common.entity.region.RegionsEntity;
import fr.basketball.statistics.location.domain.repository.region.RegionRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegionFindAllSteps {

	private RegionRepository regionRepository;

	private RegionService regionService;

	private RegionContextHolder<List<RegionEntity>, Optional<RegionsEntity>> holder;

	public RegionFindAllSteps() {
		regionRepository = mock(RegionRepository.class);
		regionService = new RegionServiceImpl(regionRepository);
		holder = new RegionContextHolder<>();
	}


	@Given("A list of regions")
	public void a_list_of_regions(List<RegionEntity> entities) {
		holder.addParam(entities);
	}
	@When("We get all regions")
	public void we_get_all_regions() {
		when(regionService.findAll())
		.thenReturn(RegionsEntity.builder().items(holder.getParam()).build());
		holder.addResult(Optional.of(regionService.findAll()));
	}
	@Then("We return {int} regions with id {string},{string}, name {string},{string}")
	public void we_return_regions_with_id_name_and_code(Integer expectedSize, String id1, String id2, String name1, String name2) {
	    assertThat(holder.getParam()).isNotNull();
	    assertThat(holder.getParam()).hasSize(expectedSize);
	    assertThat(holder.getParam().get(0).getId()).isEqualTo(Integer.parseInt(id1));
	    assertThat(holder.getParam().get(1).getId()).isEqualTo(Integer.parseInt(id2));
	    assertThat(holder.getParam().get(0).getName()).isEqualTo(name1);
	    assertThat(holder.getParam().get(1).getName()).isEqualTo(name2);
	}



}
