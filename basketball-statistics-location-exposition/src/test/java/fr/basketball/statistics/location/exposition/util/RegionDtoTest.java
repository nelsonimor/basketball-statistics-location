package fr.basketball.statistics.location.exposition.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;

import fr.basketball.statistics.location.exposition.dto.RegionDto;
import fr.basketball.statistics.location.exposition.dto.RegionsDto;

class RegionDtoTest {
	
	@Test
	void testRegionsDto() {
		RegionsDto dto1 = new RegionsDto();
		dto1.setItems(null);
		
		RegionsDto dto2 = new RegionsDto();
		dto2.setItems(null);
		
		assertThat(dto1.toString()).hasSameHashCodeAs(dto2.toString());
		assertThat(dto1.hashCode()).hasSameHashCodeAs(dto2.hashCode());
		assertEquals(dto1, dto2);
	}
	
	@Test
	void testRegiontDto() {
		RegionDto dto1 = new RegionDto();
		dto1.setId(1);
		dto1.setName("Balcan");
			
		RegionDto dto2 = new RegionDto();
		dto2.setId(1);
		dto2.setName("Balcan");


		assertNotEquals(dto1, dto2);
	}

}
