package fr.bsm.location.domain.common.entity.continent;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.entity.EqualsContractTester;

class ContinentEntityTest implements EqualsContractTester {

  @Test
  @Override
  public void testEqualsObject() {
    ContinentEntity continentEntity1 = new ContinentEntity();
    continentEntity1.setId(1);
    continentEntity1.setCode("AF");
    continentEntity1.setName("Africa");

   
    ContinentEntity continentEntity2 = new ContinentEntity();
    continentEntity2.setId(1);
    continentEntity2.setCode("AF");
    continentEntity2.setName("Africa");
    
    assertThat(continentEntity1).isEqualTo(continentEntity2).hasSameHashCodeAs(continentEntity2);
    
    continentEntity2.setId(2);
    assertThat(continentEntity1).isNotEqualTo(continentEntity2);
    
    continentEntity2.setId(null);
    assertThat(continentEntity1).isNotEqualTo(continentEntity2);
    assertThat(continentEntity1.toString()).isNotEqualTo(continentEntity2.toString());
    assertThat(continentEntity1.hashCode()).isNotEqualTo(continentEntity2.hashCode());

  }
}
