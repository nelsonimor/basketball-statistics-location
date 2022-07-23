package fr.bsm.location.domain.common.entity.continent;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import fr.bsm.location.domain.entity.EqualsContractTester;
import fr.bsm.location.domain.util.DomainDataUtil;

class ContinentsEntityTest implements EqualsContractTester {


	@Test
	@Override
	public void testEqualsObject() {
		ContinentsEntity continentsEntity1 = new ContinentsEntity();
		ContinentsEntity continentsEntity2 = new ContinentsEntity();
		assertThat(continentsEntity1).isEqualTo(continentsEntity2).hasSameHashCodeAs(continentsEntity2);
	}

	@Test
	void testIsAdded() {
		ContinentEntity continentEntity1 = DomainDataUtil.getContinentEurope();
		ContinentsEntity continentsEntity = new ContinentsEntity();
		continentsEntity.setItems(Arrays.asList(continentEntity1));
		assertThat(continentsEntity.getItems()).isNotNull();
		assertThat(continentsEntity.getItems().get(0)).isNotNull();
	}

}
