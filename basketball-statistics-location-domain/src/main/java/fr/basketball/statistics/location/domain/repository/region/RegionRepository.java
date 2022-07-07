package fr.basketball.statistics.location.domain.repository.region;

import java.util.Optional;

import fr.basketball.statistics.location.domain.common.entity.region.RegionEntity;
import fr.basketball.statistics.location.domain.common.entity.region.RegionsEntity;

public interface RegionRepository {
	
	RegionsEntity findAll();
	
	Optional<RegionEntity> findById(Integer id);

}
