package fr.bsm.location.exposition.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CityRequestDto {

	@NotNull
	private String name;

	@NotNull
	private String countryname;

}
