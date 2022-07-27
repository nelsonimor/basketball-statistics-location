package fr.bsm.location.infrastructure.repository.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bsm.location.infrastructure.data.city.CityData;

@Repository
public interface CityJpaRepository extends JpaRepository<CityData, Integer> {

}
