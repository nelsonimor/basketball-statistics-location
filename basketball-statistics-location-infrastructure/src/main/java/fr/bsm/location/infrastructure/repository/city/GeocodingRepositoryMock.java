package fr.bsm.location.infrastructure.repository.city;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.common.exception.GeocodingException;
import fr.bsm.location.domain.repository.city.GeocodingRepository;
import fr.bsm.location.infrastructure.config.SpringProfileConstants;
import fr.bsm.location.infrastructure.data.geocoding.AddressResultDto;

@Repository
@Profile(SpringProfileConstants.MOCK_GEOCODING)
public class GeocodingRepositoryMock implements GeocodingRepository {

	@Value("classpath:mock-geocoding-response.json")
	private Resource jsonFile;

	private final ObjectMapper objectMapper;

	public GeocodingRepositoryMock(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}


	@Override
	public CityEntity geocode(CityEntity cityEntity) {
		try {
			AddressResultDto[] addressResultDtos = objectMapper.readValue(jsonFile.getInputStream(), AddressResultDto[].class);
			for (int i = 0; i < addressResultDtos.length; i++) {
				AddressResultDto result = addressResultDtos[i];
				if(result.getAddress().getCity().equals(cityEntity.getName())){
					cityEntity.setLatitude(result.getLat());
					cityEntity.setLongitude(result.getLon());
					cityEntity.setState(result.getAddress().getState());
					cityEntity.setCounty(result.getAddress().getCounty());
					return cityEntity;
				}
			}
			throw new GeocodingException("No geocoding result for city : '"+cityEntity.getName()+"' and country : '"+cityEntity.getCountry().getName()+"'");
		} catch (IOException e) {
			throw new GeocodingException("No geocoding result for city : '"+cityEntity.getName()+"' and country : '"+cityEntity.getCountry().getName()+"'");
		}
	}

}
