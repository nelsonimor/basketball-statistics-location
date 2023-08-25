package fr.bsm.location.exposition.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CityQueryDto {
	
	private Integer countryId;
	private String cityName;
	
}
