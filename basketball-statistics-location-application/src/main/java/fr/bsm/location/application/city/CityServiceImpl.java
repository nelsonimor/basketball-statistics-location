package fr.bsm.location.application.city;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.common.exception.ErrorCode;
import fr.bsm.location.domain.common.exception.GeocodingException;
import fr.bsm.location.domain.repository.city.CityRepository;
import fr.bsm.location.domain.repository.city.GeocodingRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CityServiceImpl implements CityService {
	
	private final CityRepository cityRepository;
	private final GeocodingRepository geocodingRepository;
	
	public CityServiceImpl(CityRepository cityRepository,GeocodingRepository geocodingRepository) {
		this.cityRepository = cityRepository;
		this.geocodingRepository = geocodingRepository;
	}

	@Override
	public CitiesEntity findAll(Optional<Integer> countryId,Optional<String> cityName) {
		return cityRepository.findAll(countryId,cityName);
	}
	
	@Override
	public CitiesEntity findByNames(List<String> cityNames) {
		return cityRepository.findByNames(cityNames);
	}

	@Override
	public CityEntity create(CityEntity cityEntity) {
		Optional<CityEntity> cityGeocoded = geocodingRepository.geocode(cityEntity);
		if(cityGeocoded.isEmpty()) {
			log.error("No geocoding result for city : '{}' and country : '{}'",cityEntity.getName(),cityEntity.getCountry().getName());
			
			cityEntity.setState("undefined");
			cityEntity.setCounty("undefined");
			cityEntity.setLatitude(0d);
			cityEntity.setLongitude(0d);
			
			
			
			return cityRepository.create(cityEntity);
			
			
			
			//throw new GeocodingException("["+ErrorCode.ADD_CITY_NO_GEOCODING+"] No geocoding result for city : '"+cityEntity.getName()+"' and country : '"+cityEntity.getCountry().getName()+"'");
		}
		else {
			return cityRepository.create(cityGeocoded.get());
		}
	}

	@Override
	public Optional<CityEntity> findByNameAndCountry(String name, CountryEntity countryEntity) {
		return cityRepository.findByNameAndCountry(name, countryEntity);
	}

	@Override
	public Optional<CityEntity> findById(Integer id) {
		return cityRepository.findById(id);
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		cityRepository.delete(id);
	}

	@Override
	public CitiesEntity findByIds(List<Integer> list) {
		return cityRepository.findByIds(list);
	}



}
