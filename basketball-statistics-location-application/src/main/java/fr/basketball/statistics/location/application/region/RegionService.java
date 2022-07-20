package fr.basketball.statistics.location.application.region;

import java.util.Optional;

import fr.basketball.statistics.location.domain.common.entity.region.RegionEntity;
import fr.basketball.statistics.location.domain.common.entity.region.RegionsEntity;


public interface RegionService {

  RegionsEntity findAll();
  
  Optional<RegionEntity> findById(Integer id);

}