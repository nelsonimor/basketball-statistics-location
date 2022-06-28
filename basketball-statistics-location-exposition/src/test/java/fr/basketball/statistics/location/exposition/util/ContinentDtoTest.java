package fr.basketball.statistics.location.exposition.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import fr.basketball.statistics.location.exposition.dto.ContinentDto;
import fr.basketball.statistics.location.exposition.dto.ContinentsDto;

public class ContinentDtoTest {
	
	@Test
	void testContinentsDto() {
		ContinentsDto dto1 = ContinentsDto.builder().build();
		dto1.setItems(null);
		
		ContinentsDto dto2 = ContinentsDto.builder().build();
		dto2.setItems(null);
		
		assertThat(dto1.toString()).isEqualTo(dto2.toString());
		assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
		assertThat(dto1.equals(dto2));
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

		assertThat(dto1.equals(dto2));
	}

}
