package fr.bsm.location.infrastructure.repository.city;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.repository.city.GeocodingRepository;
import fr.bsm.location.infrastructure.config.SpringProfileConstants;
import fr.bsm.location.infrastructure.data.geocoding.AddressResultDto;
import lombok.extern.slf4j.Slf4j;

@Repository
@Profile(SpringProfileConstants.MOCK_GEOCODING)
@Slf4j
public class GeocodingRepositoryMock implements GeocodingRepository {

	@Value("classpath:mock-geocoding-response.json")
	private Resource jsonFile;

	private final ObjectMapper objectMapper;

	public GeocodingRepositoryMock(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}


	@Override
	public Optional<CityEntity> geocode(CityEntity cityEntity) {
		try {
		    AddressResultDto dto = Arrays.asList(objectMapper.readValue(jsonFile.getInputStream(), AddressResultDto[].class)).stream().filter(x -> x.getAddress().getCity().equals(cityEntity.getName())).findAny().orElse(null);
		    
		    if(dto == null) {
		    	return Optional.empty();
		    }
		    else {
				cityEntity.setLatitude(dto.getLat());
				cityEntity.setLongitude(dto.getLon());
				cityEntity.setState(dto.getAddress().getState());
				cityEntity.setCounty(dto.getAddress().getCounty());
				return Optional.of(cityEntity);
		    }
	
		} catch (IOException e) {
			log.error("Geocoding error",e);
			return Optional.empty();
		}
	}

}
