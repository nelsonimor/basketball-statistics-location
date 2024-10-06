package fr.bsm.location.application.city;

import java.util.List;
import java.util.Optional;

import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;


public interface CityService {

	Optional<CityEntity> findById(Integer id);

	CitiesEntity findAll(Optional<Integer> countryId,Optional<String> cityName);
	
	CitiesEntity findByNames(List<String> cityNames);

	Optional<CityEntity> findByNameAndCountry(String name,CountryEntity countryEntity);

	CityEntity create(CityEntity cityEntity);
	
	void delete(Integer id);

	CitiesEntity findByIds(List<Integer> list);

}
