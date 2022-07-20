package fr.basketball.statistics.location.application.continent;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.continent.ContinentsEntity;
import fr.bsm.location.domain.repository.continent.ContinentRepository;

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
