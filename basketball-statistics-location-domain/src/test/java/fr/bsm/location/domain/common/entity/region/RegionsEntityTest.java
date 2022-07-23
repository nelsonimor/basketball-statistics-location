package fr.bsm.location.domain.common.entity.region;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import fr.bsm.location.domain.entity.EqualsContractTester;
import fr.bsm.location.domain.util.DomainDataUtil;

class RegionsEntityTest implements EqualsContractTester {


	@Test
	@Override
	public void testEqualsObject() {
		RegionsEntity regionsEntity1 = new RegionsEntity();
		RegionsEntity regionsEntity2 = new RegionsEntity();

		assertThat(regionsEntity1).isEqualTo(regionsEntity2).hasSameHashCodeAs(regionsEntity2);

	}

	@Test
	void testIsAdded() {
		RegionEntity regionEntity1 = DomainDataUtil.getRegionWesternEurope();

		RegionsEntity regionsEntity = new RegionsEntity();
		regionsEntity.setItems(Arrays.asList(regionEntity1));

		assertThat(regionsEntity.getItems()).isNotNull();
		assertThat(regionsEntity.getItems().get(0)).isNotNull();


	}

}
