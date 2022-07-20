package fr.basketball.statistics.location.exposition.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.basketball.statistics.location.application.continent.ContinentService;
import fr.basketball.statistics.location.exposition.dto.ContinentDto;
import fr.basketball.statistics.location.exposition.dto.ContinentsDto;
import fr.basketball.statistics.location.exposition.util.ContinentDtoMapper;
import fr.bsm.location.domain.common.entity.continent.ContinentsEntity;
import fr.bsm.location.domain.common.exception.EntityNotFoundException;

@RestController
@Validated
public class ContinentResource {

	private final ContinentService continentService;
	private final ContinentDtoMapper continentDtoMapper;

	public ContinentResource(ContinentService continentService,ContinentDtoMapper continentDtoMapper) {
		this.continentService = continentService;
		this.continentDtoMapper = continentDtoMapper;
	}


	@GetMapping("/continents")
	ResponseEntity<ContinentsDto> findAll() {

		ContinentsEntity continentsEntity = continentService.findAll();
		if (continentsEntity != null && continentsEntity.getItems().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(continentDtoMapper.entityToContinentsDto(continentsEntity));
	}


	@GetMapping(value = "/continents/{continentId}")
	public ResponseEntity<ContinentDto> findById(@PathVariable Integer continentId) {
		return continentService.findById(continentId)
				.map(continentDtoMapper::entityToDto)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new EntityNotFoundException(
						"The given identifier is unknown by the system : " + continentId));
	}

}
