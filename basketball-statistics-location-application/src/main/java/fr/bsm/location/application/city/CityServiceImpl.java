package fr.bsm.location.application.city;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.domain.repository.city.CityRepository;

@Service
public class CityServiceImpl implements CityService {
	
	private final CityRepository cityRepository;
	
	public CityServiceImpl(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	@Override
	public CitiesEntity findAll(Optional<Integer> countryId) {
		return cityRepository.findAll(countryId);
	}

}
