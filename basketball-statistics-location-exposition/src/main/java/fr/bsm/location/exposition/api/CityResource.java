package fr.bsm.location.exposition.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.bsm.location.application.city.CityService;
import fr.bsm.location.application.country.CountryService;
import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.common.exception.AlreadyExistException;
import fr.bsm.location.domain.common.exception.EntityNotFoundException;
import fr.bsm.location.exposition.dto.CitiesDto;
import fr.bsm.location.exposition.dto.CityDto;
import fr.bsm.location.exposition.dto.CityQueryDto;
import fr.bsm.location.exposition.dto.CityRequestDto;
import fr.bsm.location.exposition.util.CityDtoMapper;

@RestController
@Validated
public class CityResource {

	private final CityService cityService;
	private final CountryService countryService;
	private final CityDtoMapper cityDtoMapper;

	public CityResource(CityService cityService,CityDtoMapper cityDtoMapper,CountryService countryService) {
		this.cityService = cityService;
		this.cityDtoMapper = cityDtoMapper;
		this.countryService = countryService;
	}


	@GetMapping("/cities")
	ResponseEntity<CitiesDto> findAll(CityQueryDto cityQueryDto) {

		CitiesEntity citiesEntity = cityService.findAll(Optional.ofNullable(cityQueryDto.getCountryId()));
		if (citiesEntity == null || citiesEntity.getItems()==null || citiesEntity.getItems().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cityDtoMapper.entityToCitiesDto(citiesEntity));
	}

	@PostMapping(value = "/cities")
	public ResponseEntity<CityDto> create(@Valid @RequestBody CityRequestDto cityRequestDto) {
		//check if country really exist
		Optional<CountryEntity> country = countryService.findByName(cityRequestDto.getCountryname());
		if(!country.isPresent()) {
			throw new EntityNotFoundException("No country found for countryName : "+cityRequestDto.getCountryname());
		}

		//check if city does not already exist
		Optional<CityEntity> city = cityService.findByNameAndCountry(cityRequestDto.getName(), country.get());
		if(city.isPresent()) {
		    throw new AlreadyExistException("A city already exists with name : '"+cityRequestDto.getName()+"' for country : '"+cityRequestDto.getCountryname()+"'");
		}
		
		//mapping cityRequestDto to cityEntity
		CityEntity cityEntity = cityDtoMapper.dtoToEntity(cityRequestDto);
		cityEntity.setCountry(country.get());

		//calling service creation including geocoding
		cityEntity = cityService.create(cityEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(cityDtoMapper.entityToDto(cityEntity));

	}


}
