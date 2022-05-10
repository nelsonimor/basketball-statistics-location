package fr.basketball.statistics.location.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.basketball.statistics.location.infrastructure.data.RegionData;

@Repository
public interface RegionJpaRepository extends JpaRepository<RegionData, Integer> {

}
