package fr.bsm.location.exposition.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import fr.bsm.location.exposition.dto.CitiesDto;

class CityDtoTest {
	
	@Test
	void testCitiesDto() {
		CitiesDto dto1 = new CitiesDto();
		dto1.setItems(null);
		
		CitiesDto dto2 = new CitiesDto();
		dto2.setItems(null);
		
		assertThat(dto1.toString()).hasSameHashCodeAs(dto2.toString());
		assertThat(dto1.hashCode()).hasSameHashCodeAs(dto2.hashCode());
		
		assertEquals(dto1, dto2);
	}
	

	

}
