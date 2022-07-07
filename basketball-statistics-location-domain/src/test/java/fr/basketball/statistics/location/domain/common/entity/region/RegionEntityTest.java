package fr.basketball.statistics.location.domain.common.entity.region;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import fr.basketball.statistics.location.domain.entity.EqualsContractTester;

class RegionEntityTest implements EqualsContractTester {

  @Test
  @Override
  public void testEqualsObject() {
    RegionEntity regionEntity1 = new RegionEntity();
    regionEntity1.setId(1);
    regionEntity1.setName("Balkan");

   
    
    RegionEntity regionEntity2 = new RegionEntity();
    regionEntity1.setId(1);
    regionEntity1.setName("Caucasus");
    
    assertThat(regionEntity1).isEqualTo(regionEntity2).hasSameHashCodeAs(regionEntity2);

  }
}
