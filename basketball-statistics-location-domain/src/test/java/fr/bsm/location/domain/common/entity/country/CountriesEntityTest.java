package fr.bsm.location.domain.common.entity.country;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import fr.bsm.location.domain.common.entity.country.CountriesEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.entity.EqualsContractTester;

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
		CountryEntity countryEntity1 = new CountryEntity();
		countryEntity1.setId(1);
		countryEntity1.setName("Belgium");
		countryEntity1.setCodeiso2("BE");
		countryEntity1.setCodeiso3("BEL");
		countryEntity1.setFullname("Belgium Kingdom");
		countryEntity1.setNumber("123");

		CountriesEntity countriesEntity = new CountriesEntity(Arrays.asList(countryEntity1));

		assertThat(countriesEntity.getItems()).isNotNull();
		assertThat(countriesEntity.getItems().get(0)).isNotNull();


	}

}
