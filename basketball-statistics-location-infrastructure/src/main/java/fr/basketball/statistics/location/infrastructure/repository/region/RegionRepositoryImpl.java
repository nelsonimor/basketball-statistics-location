package fr.basketball.statistics.location.infrastructure.repository.region;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import fr.basketball.statistics.location.domain.common.entity.RegionsEntity;
import fr.basketball.statistics.location.domain.repository.RegionRepository;
import fr.basketball.statistics.location.infrastructure.data.RegionData;
import fr.basketball.statistics.location.infrastructure.util.RegionsEntityMapper;

@Repository
public class RegionRepositoryImpl implements RegionRepository {
	
	private final RegionJpaRepository regionJpaRepository;
	
	private final RegionsEntityMapper regionEntityMapper;
	
	public RegionRepositoryImpl(RegionJpaRepository regionJpaRepository,RegionsEntityMapper regionEntityMapper) {
		this.regionJpaRepository = regionJpaRepository;
		this.regionEntityMapper = regionEntityMapper;
	}

	@Override
	public RegionsEntity findAll() {
		List<RegionData> regionDatas = regionJpaRepository.findAll();
        return RegionsEntity.builder()
	            .items(regionDatas.stream()
	                .map(regionEntityMapper::dataToEntity)
	                .collect(Collectors.toList()))
	            .build();
	}

}
