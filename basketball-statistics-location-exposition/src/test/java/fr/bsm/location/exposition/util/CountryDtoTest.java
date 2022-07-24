package fr.bsm.location.exposition.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;

import fr.bsm.location.exposition.dto.ContinentDto;
import fr.bsm.location.exposition.dto.CountriesDto;
import fr.bsm.location.exposition.dto.CountryDto;
import fr.bsm.location.exposition.dto.RegionDto;

class CountryDtoTest {
	
	@Test
	void testCountriesDto() {
		CountriesDto dto1 = new CountriesDto();
		dto1.setItems(null);
		
		CountriesDto dto2 = new CountriesDto();
		dto2.setItems(null);
		
		assertThat(dto1.toString()).hasSameHashCodeAs(dto2.toString());
		assertThat(dto1.hashCode()).hasSameHashCodeAs(dto2.hashCode());
		
		assertEquals(dto1, dto2);
	}
	

}
