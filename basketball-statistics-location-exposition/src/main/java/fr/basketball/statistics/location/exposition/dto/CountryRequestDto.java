package fr.basketball.statistics.location.exposition.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CountryRequestDto {
	
	private Integer continentId;
	
	private Integer regionId;

}
