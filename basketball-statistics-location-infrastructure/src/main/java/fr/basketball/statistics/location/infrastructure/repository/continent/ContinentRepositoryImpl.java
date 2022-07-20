package fr.basketball.statistics.location.infrastructure.repository.continent;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import fr.basketball.statistics.location.infrastructure.util.ContinentEntityMapper;
import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.continent.ContinentsEntity;
import fr.bsm.location.domain.repository.continent.ContinentRepository;

@Repository
public class ContinentRepositoryImpl implements ContinentRepository {
	
	private final ContinentJpaRepository continentJpaRepository;
	
	private final ContinentEntityMapper continentEntityMapper;

	public ContinentRepositoryImpl(ContinentJpaRepository continentJpaRepository,ContinentEntityMapper continentEntityMapper) {
		this.continentJpaRepository = continentJpaRepository;
		this.continentEntityMapper = continentEntityMapper;
	}

	@Override
	public ContinentsEntity findAll() {
		return ContinentsEntity.builder().items(continentJpaRepository.findAll().stream().map(continentEntityMapper::dataToEntity).collect(Collectors.toList())).build();
	}

	@Override
	public Optional<ContinentEntity> findById(Integer id) {
		return continentJpaRepository.findById(id).map(continentEntityMapper::dataToEntity);
	}

}
