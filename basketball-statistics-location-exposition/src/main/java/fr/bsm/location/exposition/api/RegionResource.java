package fr.bsm.location.exposition.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.bsm.location.application.region.RegionService;
import fr.bsm.location.domain.common.entity.region.RegionsEntity;
import fr.bsm.location.domain.common.exception.EntityNotFoundException;
import fr.bsm.location.domain.common.exception.ErrorCode;
import fr.bsm.location.exposition.dto.RegionDto;
import fr.bsm.location.exposition.dto.RegionsDto;
import fr.bsm.location.exposition.util.RegionDtoMapper;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
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
			log.error("No regions found");
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
						"["+ErrorCode.GET_REGION_UNKNOWN_BY_ID+"] The given identifier is unknown by the system : " + regionId));
	}

}
