package fr.bsm.location.application.city;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.repository.city.CityRepository;
import fr.bsm.location.domain.repository.city.GeocodingRepository;

@Service
public class CityServiceImpl implements CityService {
	
	private final CityRepository cityRepository;
	private final GeocodingRepository geocodingRepository;
	
	public CityServiceImpl(CityRepository cityRepository,GeocodingRepository geocodingRepository) {
		this.cityRepository = cityRepository;
		this.geocodingRepository = geocodingRepository;
	}

	@Override
	public CitiesEntity findAll(Optional<Integer> countryId) {
		return cityRepository.findAll(countryId);
	}

	@Override
	public CityEntity create(CityEntity cityEntity) {
		cityEntity = geocodingRepository.geocode(cityEntity);
		return cityRepository.create(cityEntity);
	}

	@Override
	public Optional<CityEntity> findByNameAndCountry(String name, CountryEntity countryEntity) {
		return cityRepository.findByNameAndCountry(name, countryEntity);
	}

}
