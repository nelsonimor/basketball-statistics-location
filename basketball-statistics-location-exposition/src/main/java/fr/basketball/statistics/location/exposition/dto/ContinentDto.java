package fr.basketball.statistics.location.exposition.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContinentDto {
	
	private Integer id;
	private String code;
	private String name;

}
