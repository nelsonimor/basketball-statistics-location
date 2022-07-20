package fr.basketball.statistics.location.infrastructure.repository.country;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import fr.basketball.statistics.location.infrastructure.data.continent.ContinentData;
import fr.basketball.statistics.location.infrastructure.data.region.RegionData;
import fr.basketball.statistics.location.infrastructure.repository.continent.ContinentJpaRepository;
import fr.basketball.statistics.location.infrastructure.repository.region.RegionJpaRepository;
import fr.basketball.statistics.location.infrastructure.util.CountryEntityMapper;
import fr.bsm.location.domain.common.entity.country.CountriesEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.repository.country.CountryRepository;

@Repository
public class CountryRepositoryImpl implements CountryRepository {
	
	private final CountryJpaRepository countryJpaRepository;
	
	private final ContinentJpaRepository continentJpaRepository;
	
	private final RegionJpaRepository regionJpaRepository;
	
	private final CountryEntityMapper countryEntityMapper;

	public CountryRepositoryImpl(CountryJpaRepository countryJpaRepository,CountryEntityMapper countryEntityMapper,ContinentJpaRepository continentJpaRepository,RegionJpaRepository regionJpaRepository) {
		this.countryJpaRepository = countryJpaRepository;
		this.countryEntityMapper = countryEntityMapper;
		this.continentJpaRepository = continentJpaRepository;
		this.regionJpaRepository = regionJpaRepository;
	}


	@Override
	public Optional<CountryEntity> findById(Integer id) {
		return countryJpaRepository.findById(id).map(countryEntityMapper::dataToEntity);
	}


	@Override
	public CountriesEntity findAll(Optional<Integer> continentId,Optional<Integer> regiontId) {
		if(continentId.isPresent() && !regiontId.isPresent()) {
			Optional<ContinentData> continent = continentJpaRepository.findById(continentId.get());
			if(continent.isPresent()) {
				return CountriesEntity.builder().items(countryJpaRepository.findByContinent(continent.get()).stream().map(countryEntityMapper::dataToEntity).collect(Collectors.toList())).build();
			}
		}
		else if(regiontId.isPresent() && !continentId.isPresent()) {
			Optional<RegionData> region = regionJpaRepository.findById(regiontId.get());
			if(region.isPresent()) {
				return CountriesEntity.builder().items(countryJpaRepository.findByRegion(region.get()).stream().map(countryEntityMapper::dataToEntity).collect(Collectors.toList())).build();
			}
		}
		else if(regiontId.isPresent() && continentId.isPresent()) {
			Optional<RegionData> region = regionJpaRepository.findById(regiontId.get());
			Optional<ContinentData> continent = continentJpaRepository.findById(continentId.get());
			if(region.isPresent() && continent.isPresent()) {
				return CountriesEntity.builder().items(countryJpaRepository.findByContinentAndRegion(continent.get(),region.get()).stream().map(countryEntityMapper::dataToEntity).collect(Collectors.toList())).build();
			}
		}
		else {
			return CountriesEntity.builder().items(countryJpaRepository.findAll().stream().map(countryEntityMapper::dataToEntity).collect(Collectors.toList())).build();
		}
		
		
		return null;

	}


	@Override
	public Optional<CountryEntity> findByName(String name) {
		return countryJpaRepository.findByName(name).map(countryEntityMapper::dataToEntity);
	}


	@Override
	public Optional<CountryEntity> findByCodeiso2(String codeiso2) {
		return countryJpaRepository.findByCodeiso2(codeiso2).map(countryEntityMapper::dataToEntity);
	}


	@Override
	public Optional<CountryEntity> findByCodeiso3(String codeiso3) {
		return countryJpaRepository.findByCodeiso3(codeiso3).map(countryEntityMapper::dataToEntity);
	}

}
