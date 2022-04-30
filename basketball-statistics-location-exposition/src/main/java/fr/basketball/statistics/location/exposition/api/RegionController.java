package fr.basketball.statistics.location.exposition.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.basketball.statistics.location.exposition.dto.RegionDto;


@RestController
public class RegionController {
	
	@GetMapping("/region")
	List<RegionDto> getAll() {
		return Arrays.asList(RegionDto.builder().id(1).name("Western Europe").build());
	}
	
}
