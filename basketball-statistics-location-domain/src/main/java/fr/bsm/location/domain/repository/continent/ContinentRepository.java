package fr.bsm.location.domain.repository.continent;

import java.util.Optional;

import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.continent.ContinentsEntity;

public interface ContinentRepository {
	
	ContinentsEntity findAll();
	
	Optional<ContinentEntity> findById(Integer id);

}
