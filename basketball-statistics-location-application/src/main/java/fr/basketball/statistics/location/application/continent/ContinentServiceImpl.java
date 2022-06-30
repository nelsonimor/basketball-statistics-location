package fr.basketball.statistics.location.application.continent;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.basketball.statistics.location.domain.common.entity.ContinentEntity;
import fr.basketball.statistics.location.domain.common.entity.ContinentsEntity;
import fr.basketball.statistics.location.domain.repository.ContinentRepository;

@Service
public class ContinentServiceImpl implements ContinentService {
	
	private final ContinentRepository continentRepository;
	
	public ContinentServiceImpl(ContinentRepository continentRepository) {
		this.continentRepository = continentRepository;
	}

	@Override
	public ContinentsEntity findAll() {
		return continentRepository.findAll();
	}

	@Override
	public Optional<ContinentEntity> findById(Integer id) {
		return continentRepository.findById(id);
	}


}
