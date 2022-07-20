package fr.basketball.statistics.location.exposition.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.basketball.statistics.location.application.region.RegionService;
import fr.basketball.statistics.location.exposition.dto.RegionDto;
import fr.basketball.statistics.location.exposition.dto.RegionsDto;
import fr.basketball.statistics.location.exposition.util.RegionDtoMapper;
import fr.bsm.location.domain.common.entity.region.RegionsEntity;
import fr.bsm.location.domain.common.exception.EntityNotFoundException;

@RestController
@Validated
public class RegionResource {

	private final RegionService regionService;
	private final RegionDtoMapper regionDtoMapper;

	public RegionResource(RegionService regionService,RegionDtoMapper regionDtoMapper) {
		this.regionService = regionService;
		this.regionDtoMapper = regionDtoMapper;
	}


	@GetMapping("/regions")
	ResponseEntity<RegionsDto> findAll() {

		RegionsEntity regionsEntity = regionService.findAll();
		if (regionsEntity != null && regionsEntity.getItems().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(regionDtoMapper.entityToRegionsDto(regionsEntity));
	}


	@GetMapping(value = "/regions/{regionId}")
	public ResponseEntity<RegionDto> findById(@PathVariable Integer regionId) {
		return regionService.findById(regionId)
				.map(regionDtoMapper::entityToDto)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new EntityNotFoundException(
						"The given identifier is unknown by the system : " + regionId));
	}

}
