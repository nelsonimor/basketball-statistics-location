package fr.bsm.location.domain.common.entity.country;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import fr.bsm.location.domain.entity.EqualsContractTester;
import fr.bsm.location.domain.util.DomainDataUtil;

class CountriesEntityTest implements EqualsContractTester {


	@Test
	@Override
	public void testEqualsObject() {
		CountriesEntity countriesEntityTest1 = new CountriesEntity();
		CountriesEntity countriesEntityTest2 = new CountriesEntity();

		assertThat(countriesEntityTest1).isEqualTo(countriesEntityTest2).hasSameHashCodeAs(countriesEntityTest2);

	}

	@Test
	void testIsAdded() {
		CountryEntity countryEntity1 = DomainDataUtil.getCountryBelgium();
		CountriesEntity countriesEntity = new CountriesEntity(Arrays.asList(countryEntity1));
		assertThat(countriesEntity.getItems()).isNotNull();
		assertThat(countriesEntity.getItems().get(0)).isNotNull();
	}

}
