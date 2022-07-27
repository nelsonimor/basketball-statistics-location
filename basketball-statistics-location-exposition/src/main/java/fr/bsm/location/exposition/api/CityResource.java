package fr.bsm.location.exposition.api;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.bsm.location.application.city.CityService;
import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.exposition.dto.CitiesDto;
import fr.bsm.location.exposition.dto.CityQueryDto;
import fr.bsm.location.exposition.util.CityDtoMapper;

@RestController
@Validated
public class CityResource {

	private final CityService cityService;
	private final CityDtoMapper cityDtoMapper;

	public CityResource(CityService cityService,CityDtoMapper cityDtoMapper) {
		this.cityService = cityService;
		this.cityDtoMapper = cityDtoMapper;
	}


	@GetMapping("/cities")
	ResponseEntity<CitiesDto> findAll(CityQueryDto cityQueryDto) {

		CitiesEntity citiesEntity = cityService.findAll(Optional.ofNullable(cityQueryDto.getCountryId()));
		if (citiesEntity != null && citiesEntity.getItems().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cityDtoMapper.entityToCitiesDto(citiesEntity));
	}


}
