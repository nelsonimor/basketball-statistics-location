package fr.basketball.statistics.location.application.region;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import fr.basketball.statistics.location.domain.common.entity.RegionEntity;
import fr.basketball.statistics.location.domain.common.entity.RegionsEntity;

@Service
public class RegionServiceImpl implements RegionService {

	@Override
	public RegionsEntity findAll() {
		RegionsEntity regionsEntity = new RegionsEntity();
		regionsEntity.setItems(Arrays.asList(RegionEntity.builder().id(1).name("Western Europe").build(),RegionEntity.builder().id(2).name("Eastern Europe").build()));
		return regionsEntity;
	}


}
