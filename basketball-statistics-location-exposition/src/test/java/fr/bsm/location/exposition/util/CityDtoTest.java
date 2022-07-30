package fr.bsm.location.exposition.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import fr.bsm.location.exposition.dto.CitiesDto;
import fr.bsm.location.exposition.dto.CityQueryDto;
import fr.bsm.location.exposition.dto.CityRequestDto;

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
	
	
	@Test
	void testCityRequestDto() {
		CityRequestDto dto1 = new CityRequestDto();
		dto1.setCountryname(ExpositionDataUtil.COUNTRY_BELGIUM_NAME);
		dto1.setName(ExpositionDataUtil.CITY_BRUSSELS_NAME);
		
		CityRequestDto dto2 = new CityRequestDto();
		dto2.setCountryname(ExpositionDataUtil.COUNTRY_BELGIUM_NAME);
		dto2.setName(ExpositionDataUtil.CITY_BRUSSELS_NAME);
		
		assertThat(dto1.toString()).hasSameHashCodeAs(dto2.toString());
		assertThat(dto1.hashCode()).hasSameHashCodeAs(dto2.hashCode());
		
		assertEquals(dto1, dto2);
		
	}
	
	@Test
	void testCityQueryDto() {
		CityQueryDto dto1 = new CityQueryDto();
		dto1.setCountryId(ExpositionDataUtil.COUNTRY_BELGIUM_ID);

		CityQueryDto dto2 = new CityQueryDto();
		dto2.setCountryId(ExpositionDataUtil.COUNTRY_BELGIUM_ID);
		
		assertThat(dto1.toString()).hasSameHashCodeAs(dto2.toString());
		assertThat(dto1.hashCode()).hasSameHashCodeAs(dto2.hashCode());
		
		assertEquals(dto1, dto2);
		
	}
	

	

}
