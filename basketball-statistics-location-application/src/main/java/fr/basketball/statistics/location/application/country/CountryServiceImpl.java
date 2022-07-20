package fr.basketball.statistics.location.application.country;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.basketball.statistics.location.domain.common.entity.country.CountriesEntity;
import fr.basketball.statistics.location.domain.common.entity.country.CountryEntity;
import fr.basketball.statistics.location.domain.repository.country.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService {
	
	private final CountryRepository countryRepository;
	
	public CountryServiceImpl(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}


	@Override
	public Optional<CountryEntity> findById(Integer id) {
		return countryRepository.findById(id);
	}


	@Override
	public CountriesEntity findAll(Optional<Integer> continentId,Optional<Integer> regionId) {
		return countryRepository.findAll(continentId,regionId);
	}


	@Override
	public Optional<CountryEntity> findByName(String name) {
		return countryRepository.findByName(name);
	}


	@Override
	public Optional<CountryEntity> findByCodeiso2(String codeiso2) {
		return countryRepository.findByCodeiso2(codeiso2);
	}


	@Override
	public Optional<CountryEntity> findByCodeiso3(String codeiso3) {
		return countryRepository.findByCodeiso3(codeiso3);
	}


}
