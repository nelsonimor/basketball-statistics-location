package fr.basketball.statistics.location.infrastructure.repository.region;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import fr.basketball.statistics.location.domain.common.entity.region.RegionEntity;
import fr.basketball.statistics.location.domain.common.entity.region.RegionsEntity;
import fr.basketball.statistics.location.domain.repository.region.RegionRepository;
import fr.basketball.statistics.location.infrastructure.util.RegionEntityMapper;

@Repository
public class RegionRepositoryImpl implements RegionRepository {
	
	private final RegionJpaRepository regionJpaRepository;
	
	private final RegionEntityMapper regionEntityMapper;

	public RegionRepositoryImpl(RegionJpaRepository continentJpaRepository,RegionEntityMapper regionEntityMapper) {
		this.regionJpaRepository = continentJpaRepository;
		this.regionEntityMapper = regionEntityMapper;
	}

	@Override
	public RegionsEntity findAll() {
		return RegionsEntity.builder().items(regionJpaRepository.findAll().stream().map(regionEntityMapper::dataToEntity).collect(Collectors.toList())).build();
	}

	@Override
	public Optional<RegionEntity> findById(Integer id) {
		return regionJpaRepository.findById(id).map(regionEntityMapper::dataToEntity);
	}

}
