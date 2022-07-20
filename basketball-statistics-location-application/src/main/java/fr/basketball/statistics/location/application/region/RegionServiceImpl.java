package fr.basketball.statistics.location.application.region;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.bsm.location.domain.common.entity.region.RegionEntity;
import fr.bsm.location.domain.common.entity.region.RegionsEntity;
import fr.bsm.location.domain.repository.region.RegionRepository;

@Service
public class RegionServiceImpl implements RegionService {
	
	private final RegionRepository regionRepository;
	
	public RegionServiceImpl(RegionRepository regionRepository) {
		this.regionRepository = regionRepository;
	}

	@Override
	public RegionsEntity findAll() {
		return regionRepository.findAll();
	}

	@Override
	public Optional<RegionEntity> findById(Integer id) {
		return regionRepository.findById(id);
	}


}
