package fr.bsm.location.domain.repository.city;

import fr.bsm.location.domain.common.entity.city.CityEntity;

public interface GeocodingRepository {
	
	CityEntity geocode(CityEntity cityEntity);
	
}
