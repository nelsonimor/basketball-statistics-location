package fr.basketball.statistics.location.domain.repository.continent;

import java.util.Optional;

import fr.basketball.statistics.location.domain.common.entity.continent.ContinentEntity;
import fr.basketball.statistics.location.domain.common.entity.continent.ContinentsEntity;

public interface ContinentRepository {
	
	ContinentsEntity findAll();
	
	Optional<ContinentEntity> findById(Integer id);

}
