package fr.bsm.location.exposition.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import fr.bsm.location.exposition.dto.CountryRequestDto;

class CountryRequestDtoTest {
	
	@Test
	void testCountryRequestDto() {
		CountryRequestDto dto1 = new CountryRequestDto();
		dto1.setContinentId(1);
		dto1.setRegionId(2);
		
		CountryRequestDto dto2 = new CountryRequestDto();
		dto2.setContinentId(1);
		dto2.setRegionId(2);
		
		assertThat(dto1.toString()).hasSameHashCodeAs(dto2.toString());
		assertThat(dto1.hashCode()).hasSameHashCodeAs(dto2.hashCode());
		
		assertEquals(dto1, dto2);
	}
	

}
