package fr.basketball.statistics.location.application.continent;

import java.util.Optional;

import fr.basketball.statistics.location.domain.common.entity.ContinentEntity;
import fr.basketball.statistics.location.domain.common.entity.ContinentsEntity;


public interface ContinentService {

  ContinentsEntity findAll();
  
  Optional<ContinentEntity> findById(Integer id);

}
