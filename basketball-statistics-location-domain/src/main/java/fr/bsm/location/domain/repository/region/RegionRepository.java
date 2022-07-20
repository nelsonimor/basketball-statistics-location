package fr.bsm.location.domain.repository.region;

import java.util.Optional;

import fr.bsm.location.domain.common.entity.region.RegionEntity;
import fr.bsm.location.domain.common.entity.region.RegionsEntity;

public interface RegionRepository {
	
	RegionsEntity findAll();
	
	Optional<RegionEntity> findById(Integer id);

}
