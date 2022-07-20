package fr.basketball.statistics.location.infrastructure.repository.country;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.basketball.statistics.location.infrastructure.data.continent.ContinentData;
import fr.basketball.statistics.location.infrastructure.data.country.CountryData;
import fr.basketball.statistics.location.infrastructure.data.region.RegionData;

@Repository
public interface CountryJpaRepository extends JpaRepository<CountryData, Integer> {
	
	Optional<CountryData> findByName(String name);
	
	Optional<CountryData> findByCodeiso2(String codeiso2);
	
	Optional<CountryData> findByCodeiso3(String codeiso3);
	
	List<CountryData> findByContinent(ContinentData continent);
	
	List<CountryData> findByRegion(RegionData region);
	
	List<CountryData> findByContinentAndRegion(ContinentData continent,RegionData region);

}
