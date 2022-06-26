package fr.basketball.statistics.location.exposition.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.basketball.statistics.location.application.region.RegionService;
import fr.basketball.statistics.location.domain.common.entity.RegionsEntity;
import fr.basketball.statistics.location.exposition.dto.RegionsDto;
import fr.basketball.statistics.location.exposition.util.RegionDtoMapper;



@RestController
@Validated
public class RegionResource {

	private final RegionService regionService;
	private final RegionDtoMapper regionDtoMapper;
	
	public RegionResource(RegionService regionService,RegionDtoMapper regionDtoMapper) {
		this.regionService = regionService;
		this.regionDtoMapper = regionDtoMapper;
	}


	@GetMapping("/region")
	ResponseEntity<RegionsDto> findAll() {
	    RegionsEntity regionsEntity = regionService.findAll();
	    if (regionsEntity != null && regionsEntity.getItems().isEmpty()) {
	      return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(regionDtoMapper.entityToRegionsDto(regionsEntity));
	}

}
