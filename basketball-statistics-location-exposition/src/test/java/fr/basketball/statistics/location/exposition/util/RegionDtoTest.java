package fr.basketball.statistics.location.exposition.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import fr.basketball.statistics.location.exposition.dto.RegionDto;
import fr.basketball.statistics.location.exposition.dto.RegionsDto;

public class RegionDtoTest {
	
	@Test
	void testRegionsDto() {
		RegionsDto dto1 = new RegionsDto();
		dto1.setItems(null);
		
		RegionsDto dto2 = new RegionsDto();
		dto2.setItems(null);
		
		assertThat(dto1.toString()).isEqualTo(dto2.toString());
		assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
		assertThat(dto1.equals(dto2));
	}
	
	@Test
	void testRegiontDto() {
		RegionDto dto1 = new RegionDto();
		dto1.setId(1);
		dto1.setName("Balcan");
			
		RegionDto dto2 = new RegionDto();
		dto2.setId(1);
		dto2.setName("Balcan");


		assertThat(dto1.equals(dto2));
	}

}
