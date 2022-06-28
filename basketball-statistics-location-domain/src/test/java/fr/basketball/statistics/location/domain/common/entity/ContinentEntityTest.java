package fr.basketball.statistics.location.domain.common.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import fr.basketball.statistics.location.domain.entity.EqualsContractTester;

class ContinentEntityTest implements EqualsContractTester {

  @Test
  @Override
  public void testEqualsObject() {
    ContinentEntity continentEntity1 = new ContinentEntity();
    continentEntity1.setId(1);
    continentEntity1.setCode("AF");
    continentEntity1.setName("Africa");

   
    
    ContinentEntity continentEntity2 = new ContinentEntity();
    continentEntity1.setId(1);
    continentEntity1.setCode("AF");
    continentEntity1.setName("Africa");
    
    assertThat(continentEntity1).isEqualTo(continentEntity2).hasSameHashCodeAs(continentEntity2);

  }
}
