package fr.bsm.location.exposition.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {

	private Integer id;
	private String name;
	private Double longitude;
	private Double latitude;
	private String state;
	private String county;
	private CountryDto country;

}
