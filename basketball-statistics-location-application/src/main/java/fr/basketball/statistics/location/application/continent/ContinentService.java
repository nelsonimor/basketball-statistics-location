package fr.basketball.statistics.location.application.continent;

import java.util.Optional;

import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.continent.ContinentsEntity;


public interface ContinentService {

  ContinentsEntity findAll();
  
  Optional<ContinentEntity> findById(Integer id);

}
