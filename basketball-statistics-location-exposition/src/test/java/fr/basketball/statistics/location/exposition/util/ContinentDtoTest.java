package fr.basketball.statistics.location.exposition.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;

import fr.basketball.statistics.location.exposition.dto.ContinentDto;
import fr.basketball.statistics.location.exposition.dto.ContinentsDto;

class ContinentDtoTest {
	
	@Test
	void testContinentsDto() {
		ContinentsDto dto1 = new ContinentsDto();
		dto1.setItems(null);
		
		ContinentsDto dto2 = new ContinentsDto();
		dto2.setItems(null);
		
		assertThat(dto1.toString()).hasSameHashCodeAs(dto2.toString());
		assertThat(dto1.hashCode()).hasSameHashCodeAs(dto2.hashCode());
		
		assertEquals(dto1, dto2);
	}
	
	@Test
	void testContinentDto() {
		ContinentDto dto1 = new ContinentDto();
		dto1.setId(1);
		dto1.setName("Africa");
		dto1.setCode("AF");
			
		ContinentDto dto2 = new ContinentDto();
		dto2.setId(1);
		dto2.setName("Africa");
		dto2.setCode("AF");

		assertNotEquals(dto1, dto2);
	}

}
