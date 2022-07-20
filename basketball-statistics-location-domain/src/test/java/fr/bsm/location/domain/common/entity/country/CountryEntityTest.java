package fr.bsm.location.domain.common.entity.country;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.common.entity.region.RegionEntity;
import fr.bsm.location.domain.entity.EqualsContractTester;

class CountryEntityTest implements EqualsContractTester {

  @Test
  @Override
  public void testEqualsObject() {
    ContinentEntity continentEntity1 = new ContinentEntity();
    continentEntity1.setId(1);
    continentEntity1.setCode("EU");
    continentEntity1.setName("Europe");
    
    RegionEntity regionEntity1 = new RegionEntity();
    regionEntity1.setId(1);
    regionEntity1.setName("Western Europe");
    
    CountryEntity countryEntity1 = new CountryEntity();
    countryEntity1.setId(1);
    countryEntity1.setRegion(regionEntity1);
    countryEntity1.setContinent(continentEntity1);
    countryEntity1.setName("France");
    countryEntity1.setFullname("French Republic");
    countryEntity1.setCodeiso2("FR");
    countryEntity1.setCodeiso3("FRA");
    countryEntity1.setNumber("123");
    
    CountryEntity countryEntity2 = new CountryEntity(1,"France","French Republic","FR","FRA","123",continentEntity1,regionEntity1);

    assertThat(countryEntity1).isEqualTo(countryEntity2).hasSameHashCodeAs(countryEntity2);
    
    countryEntity2.setId(2);
    assertThat(countryEntity1).isNotEqualTo(countryEntity2);

    countryEntity2.setId(null);
    assertThat(countryEntity1).isNotEqualTo(countryEntity2);
    assertThat(countryEntity1.toString()).isNotEqualTo(countryEntity2.toString());
    assertThat(countryEntity1.hashCode()).isNotEqualTo(countryEntity2.hashCode());

  }
}
