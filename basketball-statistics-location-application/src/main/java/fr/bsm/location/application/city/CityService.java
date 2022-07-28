package fr.bsm.location.application.city;

import java.util.Optional;

import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;


public interface CityService {

  CitiesEntity findAll(Optional<Integer> countryId);
  
  Optional<CityEntity> findByNameAndCountry(String name,CountryEntity countryEntity);
  
  CityEntity create(CityEntity cityEntity);
  
}
