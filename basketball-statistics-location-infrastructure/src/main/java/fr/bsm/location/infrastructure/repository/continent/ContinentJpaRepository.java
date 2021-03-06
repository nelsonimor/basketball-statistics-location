package fr.bsm.location.infrastructure.repository.continent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bsm.location.infrastructure.data.continent.ContinentData;

@Repository
public interface ContinentJpaRepository extends JpaRepository<ContinentData, Integer> {

}
