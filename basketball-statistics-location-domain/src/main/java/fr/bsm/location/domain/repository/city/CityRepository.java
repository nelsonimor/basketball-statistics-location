package fr.bsm.location.domain.repository.city;

import java.util.Optional;

import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;

public interface CityRepository {
	
	Optional<CityEntity> findById(Integer id);
	
	void delete(Integer id);
	
	CitiesEntity findAll(Optional<Integer> countryId,Optional<String> cityName);
	
	CityEntity create(CityEntity cityEntity);
	
	Optional<CityEntity> findByNameAndCountry(String name,CountryEntity countryEntity);


}
