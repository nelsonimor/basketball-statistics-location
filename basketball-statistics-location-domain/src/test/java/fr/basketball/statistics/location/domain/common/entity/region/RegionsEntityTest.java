package fr.basketball.statistics.location.domain.common.entity.region;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import fr.basketball.statistics.location.domain.entity.EqualsContractTester;

public class RegionsEntityTest implements EqualsContractTester {


	@Test
	@Override
	public void testEqualsObject() {
		RegionsEntity regionsEntity1 = new RegionsEntity();
		RegionsEntity regionsEntity2 = new RegionsEntity();

		assertThat(regionsEntity1).isEqualTo(regionsEntity2).hasSameHashCodeAs(regionsEntity2);

	}

	@Test
	void testIsAdded() {
		RegionEntity regionEntity1 = new RegionEntity();
		regionEntity1.setId(1);
		regionEntity1.setName("Balkan");

		RegionsEntity regionsEntity = new RegionsEntity();
		regionsEntity.setItems(Arrays.asList(regionEntity1));

		assertThat(regionsEntity.getItems()).isNotNull();
		assertThat(regionsEntity.getItems().get(0)).isNotNull();


	}

}
