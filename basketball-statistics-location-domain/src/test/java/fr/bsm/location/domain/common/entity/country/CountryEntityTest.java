package fr.bsm.location.domain.common.entity.country;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import fr.bsm.location.domain.entity.EqualsContractTester;
import fr.bsm.location.domain.util.DomainDataUtil;

class CountryEntityTest implements EqualsContractTester {

  @Test
  @Override
  public void testEqualsObject() {
    CountryEntity countryEntity1 = DomainDataUtil.getCountryBelgium();
    CountryEntity countryEntity2 = DomainDataUtil.getCountryBelgium();
    assertThat(countryEntity1).isEqualTo(countryEntity2).hasSameHashCodeAs(countryEntity2);
    
    countryEntity2.setId(2);
    assertThat(countryEntity1).isNotEqualTo(countryEntity2);

    countryEntity2.setId(null);
    assertThat(countryEntity1).isNotEqualTo(countryEntity2);
    assertThat(countryEntity1.toString()).isNotEqualTo(countryEntity2.toString());
    assertThat(countryEntity1.hashCode()).isNotEqualTo(countryEntity2.hashCode());

  }
}
