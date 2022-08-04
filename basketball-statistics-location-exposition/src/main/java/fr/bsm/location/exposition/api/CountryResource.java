package fr.bsm.location.exposition.api;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.bsm.location.application.country.CountryService;
import fr.bsm.location.domain.common.entity.country.CountriesEntity;
import fr.bsm.location.domain.common.exception.EntityNotFoundException;
import fr.bsm.location.exposition.dto.CountriesDto;
import fr.bsm.location.exposition.dto.CountryDto;
import fr.bsm.location.exposition.dto.CountryRequestDto;
import fr.bsm.location.exposition.util.CountryDtoMapper;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
public class CountryResource {

	private final CountryService countryService;
	private final CountryDtoMapper countryDtoMapper;

	public CountryResource(CountryService countryService,CountryDtoMapper countryDtoMapper) {
		this.countryService = countryService;
		this.countryDtoMapper = countryDtoMapper;
	}

	@GetMapping("/countries")
	ResponseEntity<CountriesDto> findAll(CountryRequestDto countryRequestDto) {
		CountriesEntity countriesEntity = countryService.findAll(
				Optional.ofNullable(countryRequestDto.getContinentId()),
				Optional.ofNullable(countryRequestDto.getRegionId())
				);
		if (countriesEntity == null || countriesEntity.getItems()==null || countriesEntity.getItems().isEmpty()) {
			log.error("No countries found");
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(countryDtoMapper.entityToCountriesDto(countriesEntity));
	}




	@GetMapping(value = "/countries/id/{countryId}")
	public ResponseEntity<CountryDto> findById(@PathVariable Integer countryId) {
		return countryService.findById(countryId)
				.map(countryDtoMapper::entityToDto)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new EntityNotFoundException(
						"The given identifier is unknown by the system : " + countryId));
	}
	
	@GetMapping(value = "/countries/name/{countryName}")
	public ResponseEntity<CountryDto> findByName(@PathVariable String countryName) {
		return countryService.findByName(countryName)
				.map(countryDtoMapper::entityToDto)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new EntityNotFoundException(
						"The given countryName is unknown by the system : " + countryName));
	}
	
	@GetMapping(value = "/countries/codeiso2/{countryCodeiso2}")
	public ResponseEntity<CountryDto> findByCodeiso2(@PathVariable String countryCodeiso2) {
		return countryService.findByCodeiso2(countryCodeiso2)
				.map(countryDtoMapper::entityToDto)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new EntityNotFoundException(
						"The given countryCodeiso2 is unknown by the system : " + countryCodeiso2));
	}
	
	@GetMapping(value = "/countries/codeiso3/{countryCodeiso3}")
	public ResponseEntity<CountryDto> findByCodeiso3(@PathVariable String countryCodeiso3) {
		return countryService.findByCodeiso3(countryCodeiso3)
				.map(countryDtoMapper::entityToDto)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new EntityNotFoundException(
						"The given countryCodeiso3 is unknown by the system : " + countryCodeiso3));
	}

}
