package fr.bsm.location.domain.repository.city;

import java.util.Optional;

import fr.bsm.location.domain.common.entity.city.CitiesEntity;

public interface CityRepository {
	
	CitiesEntity findAll(Optional<Integer> countryId);


}
