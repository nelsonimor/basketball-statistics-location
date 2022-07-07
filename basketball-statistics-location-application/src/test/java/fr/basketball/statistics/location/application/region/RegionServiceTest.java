package fr.basketball.statistics.location.application.region;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.basketball.statistics.location.domain.common.entity.region.RegionEntity;
import fr.basketball.statistics.location.domain.common.entity.region.RegionsEntity;
import fr.basketball.statistics.location.domain.repository.region.RegionRepository;

@ExtendWith(MockitoExtension.class)
class RegionServiceTest {

	@Mock
	RegionRepository regionRepository;

	RegionService regionService;

	private Integer REGION_BALKAN_ID = 1;
	private String REGION_BALKAN_NAME = "Balkan Peninsula";

	private Integer REGION_CAUCASUS_ID = 2;
	private String REGION_CAUCASUS_NAME = "Caucasus";

	@BeforeEach
	void setUp() {
		regionService = new RegionServiceImpl(regionRepository);
	}

	@Test
	void testFindAll() {
		List<RegionEntity> entities = createEntityItems();
		RegionsEntity entityToReturn = RegionsEntity.builder().items(entities).build();
		when(regionRepository.findAll()).thenReturn(entityToReturn);
		RegionsEntity result = regionService.findAll();
		assertThat(result.getItems()).hasSize(2);
		verify(regionRepository).findAll();
	}
	
	@Test
	void testFindById() {
		when(regionRepository.findById(REGION_CAUCASUS_ID)).thenReturn(Optional.of(createRegionEntity(REGION_CAUCASUS_ID,REGION_CAUCASUS_NAME)));
		Optional<RegionEntity> result = regionService.findById(REGION_CAUCASUS_ID);
		assertThat(result).isNotNull();
		assertThat(result.get()).isNotNull();
		assertThat(result.get().getId()).isEqualTo(REGION_CAUCASUS_ID);
	}


	private List<RegionEntity> createEntityItems() {
		return Arrays.asList(createRegionEntity(REGION_BALKAN_ID,REGION_BALKAN_NAME), createRegionEntity(REGION_CAUCASUS_ID,REGION_CAUCASUS_NAME));
	}
	
	private RegionEntity createRegionEntity(Integer id,String name) {
		return RegionEntity.builder()
				.id(id)
				.name(name)
				.build();
	}


}
