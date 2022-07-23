package fr.bsm.location.domain.common.entity.region;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import fr.bsm.location.domain.entity.EqualsContractTester;
import fr.bsm.location.domain.util.DomainDataUtil;

class RegionEntityTest implements EqualsContractTester {

  @Test
  @Override
  public void testEqualsObject() {
	    RegionEntity regionEntity1 = DomainDataUtil.getRegionWesternEurope();
	    RegionEntity regionEntity2 = DomainDataUtil.getRegionWesternEurope();
	    
	    assertThat(regionEntity1).isEqualTo(regionEntity2).hasSameHashCodeAs(regionEntity2);
	    
	    regionEntity2.setId(2);
	    assertThat(regionEntity1).isNotEqualTo(regionEntity2);
	    
	    regionEntity2.setId(null);
	    assertThat(regionEntity1).isNotEqualTo(regionEntity2);
	    assertThat(regionEntity1.toString()).isNotEqualTo(regionEntity2.toString());
	    assertThat(regionEntity1.hashCode()).isNotEqualTo(regionEntity2.hashCode());

  }
}
