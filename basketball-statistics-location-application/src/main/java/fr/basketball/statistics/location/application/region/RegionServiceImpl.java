package fr.basketball.statistics.location.application.region;

import org.springframework.stereotype.Service;

import fr.basketball.statistics.location.domain.common.entity.RegionsEntity;
import fr.basketball.statistics.location.domain.repository.RegionRepository;

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


}
