package fr.bsm.location.infrastructure.repository.city;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.repository.city.CityRepository;
import fr.bsm.location.infrastructure.data.city.CityData;
import fr.bsm.location.infrastructure.data.country.CountryData;
import fr.bsm.location.infrastructure.repository.country.CountryJpaRepository;
import fr.bsm.location.infrastructure.util.CityEntityMapper;
import fr.bsm.location.infrastructure.util.CountryEntityMapper;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CityRepositoryImpl implements CityRepository {
	
	private final CityJpaRepository cityJpaRepository;
	
	private final CityEntityMapper cityEntityMapper;
	
	private final CountryEntityMapper countryEntityMapper;
	
	private final CountryJpaRepository countryJpaRepository;

	public CityRepositoryImpl(CityJpaRepository cityJpaRepository,CityEntityMapper cityEntityMapper,CountryJpaRepository countryJpaRepository,CountryEntityMapper countryEntityMapper) {
		this.cityJpaRepository = cityJpaRepository;
		this.cityEntityMapper = cityEntityMapper;
		this.countryJpaRepository = countryJpaRepository;
		this.countryEntityMapper = countryEntityMapper;
	}
	
	private Optional<CountryData> getCountry(Integer countryId) {
		Optional<CountryData> countryData = countryJpaRepository.findById(countryId);
		return countryData;
	}


	@Override
	public CitiesEntity findAll(Optional<Integer> countryId,Optional<String> cityName) {
		if(countryId.isPresent() && cityName.isPresent()) {
			Optional<CountryData> countryData = getCountry(countryId.get());
			if(!countryData.isPresent()) {
				log.error("No country found for id : {}",countryId.get());
				return null;
			}
			else {
				return CitiesEntity.builder().items(cityJpaRepository.findByNameAndCountry(cityName.get(), countryData.get()).stream().map(cityEntityMapper::dataToEntity).collect(Collectors.toList())).build();
			}
		}
		else if(countryId.isPresent() && !cityName.isPresent()) {
			Optional<CountryData> countryData = getCountry(countryId.get());
			if(countryData.isPresent()) {
				return CitiesEntity.builder().items(cityJpaRepository.findByCountry(countryData.get()).stream().map(cityEntityMapper::dataToEntity).collect(Collectors.toList())).build();
			}
			else {
				log.error("No country found for id : {}",countryId.get());
				return null;
			}
		}
		else if(!countryId.isPresent() && cityName.isPresent()) {
			return CitiesEntity.builder().items(cityJpaRepository.findByName(cityName.get()).stream().map(cityEntityMapper::dataToEntity).collect(Collectors.toList())).build();
		}
		
		return CitiesEntity.builder().items(cityJpaRepository.findAll().stream().map(cityEntityMapper::dataToEntity).collect(Collectors.toList())).build();
	}


	@Override
	public CityEntity create(CityEntity cityEntity) {
		CityData cityData = cityEntityMapper.entityToData(cityEntity);
		cityData.setCreationDate(new Timestamp(System.currentTimeMillis()));
		return cityEntityMapper.dataToEntity(cityJpaRepository.save(cityData));
	}


	@Override
	public Optional<CityEntity> findByNameAndCountry(String name, CountryEntity countryEntity) {
		return cityJpaRepository.findByNameAndCountry(name,countryEntityMapper.entityToData(countryEntity)).map(cityEntityMapper::dataToEntity);
	}


	@Override
	public Optional<CityEntity> findById(Integer id) {
		return cityJpaRepository.findById(id).map(cityEntityMapper::dataToEntity);
	}


	@Override
	public void delete(Integer id) {
		cityJpaRepository.deleteById(id);
	}



}
