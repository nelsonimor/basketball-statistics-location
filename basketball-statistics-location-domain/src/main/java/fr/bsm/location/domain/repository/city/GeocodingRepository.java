package fr.bsm.location.domain.repository.city;

import java.util.Optional;

import fr.bsm.location.domain.common.entity.city.CityEntity;

public interface GeocodingRepository {
	
	Optional<CityEntity> geocode(CityEntity cityEntity);
	
}
