package fr.bsm.location.domain.repository.country;

import java.util.Optional;

import fr.bsm.location.domain.common.entity.country.CountriesEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;

public interface CountryRepository {
	
	CountriesEntity findAll(Optional<Integer> continentId,Optional<Integer> regionId);

	Optional<CountryEntity> findById(Integer id);
	
	Optional<CountryEntity> findByName(String name);
	
	Optional<CountryEntity> findByCodeiso2(String codeiso2);
	
	Optional<CountryEntity> findByCodeiso3(String codeiso3);
	


}
