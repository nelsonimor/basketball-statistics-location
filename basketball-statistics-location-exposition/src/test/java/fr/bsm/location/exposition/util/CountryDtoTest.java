package fr.bsm.location.exposition.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import fr.bsm.location.exposition.dto.CountriesDto;
import fr.bsm.location.exposition.dto.CountryDto;

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
	
	@Test
	void testCountryDto() {
		CountryDto dto1 = new CountryDto();
		dto1.setName("France");
		dto1.setCodeiso2("FR");
		dto1.setCodeiso3("FRA");
		dto1.setFullname("French Rep.");
		dto1.setNumber("123");
		
		CountryDto dto2 = new CountryDto();
		dto2.setName("France");
		dto2.setCodeiso2("FR");
		dto2.setCodeiso3("FRA");
		dto2.setFullname("French Rep.");
		dto2.setNumber("123");
		
		
		assertThat(dto1.toString()).hasSameHashCodeAs(dto2.toString());
		assertThat(dto1.hashCode()).hasSameHashCodeAs(dto2.hashCode());
		
		assertEquals(dto1, dto2);
	}
	

}
