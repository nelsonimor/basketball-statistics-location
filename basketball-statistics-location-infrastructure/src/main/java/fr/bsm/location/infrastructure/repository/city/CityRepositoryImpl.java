package fr.bsm.location.infrastructure.repository.city;

import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import fr.bsm.location.domain.common.entity.city.CitiesEntity;
import fr.bsm.location.domain.repository.city.CityRepository;
import fr.bsm.location.infrastructure.util.CityEntityMapper;

@Repository
public class CityRepositoryImpl implements CityRepository {
	
	private final CityJpaRepository cityJpaRepository;
	
	private final CityEntityMapper cityEntityMapper;

	public CityRepositoryImpl(CityJpaRepository cityJpaRepository,CityEntityMapper cityEntityMapper) {
		this.cityJpaRepository = cityJpaRepository;
		this.cityEntityMapper = cityEntityMapper;
	}


	@Override
	public CitiesEntity findAll() {
		return CitiesEntity.builder().items(cityJpaRepository.findAll().stream().map(cityEntityMapper::dataToEntity).collect(Collectors.toList())).build();
	}



}
