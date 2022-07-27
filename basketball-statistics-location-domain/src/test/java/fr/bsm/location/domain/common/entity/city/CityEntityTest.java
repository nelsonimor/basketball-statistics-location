package fr.bsm.location.domain.common.entity.city;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import fr.bsm.location.domain.entity.EqualsContractTester;
import fr.bsm.location.domain.util.DomainDataUtil;

class CityEntityTest implements EqualsContractTester {

  @Test
  @Override
  public void testEqualsObject() {
    CityEntity entity1 = DomainDataUtil.getCityBrussels();
    CityEntity entity2 = DomainDataUtil.getCityBrussels();
    assertThat(entity1).isEqualTo(entity2).hasSameHashCodeAs(entity2);
    
    entity2.setId(2);
    assertThat(entity1).isNotEqualTo(entity2);
    
    entity2.setId(null);
    assertThat(entity1).isNotEqualTo(entity2);
    assertThat(entity1.toString()).isNotEqualTo(entity2.toString());
    assertThat(entity1.hashCode()).isNotEqualTo(entity2.hashCode());

  }
}
