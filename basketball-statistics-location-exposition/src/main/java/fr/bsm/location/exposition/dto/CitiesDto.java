package fr.bsm.location.exposition.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CitiesDto {
	
	  @NotNull
	  private List<CityDto> items;

}
