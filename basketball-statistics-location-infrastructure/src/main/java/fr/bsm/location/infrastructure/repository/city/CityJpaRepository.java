package fr.bsm.location.infrastructure.repository.city;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bsm.location.infrastructure.data.city.CityData;
import fr.bsm.location.infrastructure.data.country.CountryData;

@Repository
public interface CityJpaRepository extends JpaRepository<CityData, Integer> {
	
	List<CityData> findByCountry(CountryData continent);
	
	Optional<CityData> findByNameAndCountry(String name,CountryData country);
	
	Optional<CityData> findByName(String name);

}
