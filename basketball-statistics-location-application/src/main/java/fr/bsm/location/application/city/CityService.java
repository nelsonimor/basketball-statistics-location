package fr.bsm.location.application.city;

import java.util.Optional;

import fr.bsm.location.domain.common.entity.city.CitiesEntity;


public interface CityService {

  CitiesEntity findAll(Optional<Integer> countryId);
  
}
