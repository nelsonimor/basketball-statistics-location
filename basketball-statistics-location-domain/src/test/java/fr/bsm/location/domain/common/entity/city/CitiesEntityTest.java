package fr.bsm.location.domain.common.entity.city;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import fr.bsm.location.domain.entity.EqualsContractTester;
import fr.bsm.location.domain.util.DomainDataUtil;

class CitiesEntityTest implements EqualsContractTester {


	@Test
	@Override
	public void testEqualsObject() {
		CitiesEntity entities1 = new CitiesEntity();
		CitiesEntity entities2 = new CitiesEntity();
		assertThat(entities1).isEqualTo(entities2).hasSameHashCodeAs(entities2);
	}

	@Test
	void testIsAdded() {
		CityEntity entity1 = DomainDataUtil.getCityBrussels();
		CitiesEntity entities = new CitiesEntity();
		entities.setItems(Arrays.asList(entity1));
		assertThat(entities.getItems()).isNotNull();
		assertThat(entities.getItems().get(0)).isNotNull();
	}

}
