package fr.bsm.location.infrastructure.repository.city;



import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.repository.city.GeocodingRepository;
import fr.bsm.location.infrastructure.config.GeocodingProperties;
import fr.bsm.location.infrastructure.config.SpringProfileConstants;
import fr.bsm.location.infrastructure.data.geocoding.AddressDto;
import fr.bsm.location.infrastructure.data.geocoding.AddressResultDto;

@Repository
@Profile(SpringProfileConstants.UNMOCK_GEOCODING)
public class GeocodingRepositoryImpl implements GeocodingRepository {
	
	private static final String NA = "Not available";
	
	private final GeocodingProperties geocodingProperties;
	
	public GeocodingRepositoryImpl(GeocodingProperties geocodingProperties) {
		this.geocodingProperties = geocodingProperties;
	}

	@Override
	public Optional<CityEntity> geocode(CityEntity cityEntity) {
		AddressResultDto[] results = WebClient.builder()
				.baseUrl(geocodingProperties.getUrl())
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build().get()
				.uri(uriBuilder -> uriBuilder
						.path("/")
						.queryParam("addressdetails", geocodingProperties.getAddressdetails())
						.queryParam("q", cityEntity.getName()+","+cityEntity.getCountry().getName())
						.queryParam("format",geocodingProperties.getFormat())
						.queryParam("limit", geocodingProperties.getLimit())
						.queryParam("accept-language", geocodingProperties.getAcceptlanguage())
						.build())
				.retrieve()
				.bodyToMono(AddressResultDto[].class).share()
				.block();

		
		if(results == null || results.length == 0) {
			return Optional.empty();
		}
		
		AddressResultDto result = results[0];
		cityEntity.setLatitude(result.getLat());
		cityEntity.setLongitude(result.getLon());
		performState(cityEntity, result.getAddress());
		performCounty(cityEntity, result.getAddress());
		return Optional.of(cityEntity);
	}
	
	
	private void performState(CityEntity cityEntity,AddressDto addressDto) {
		if(addressDto.getState()!=null) {
			cityEntity.setState(addressDto.getState());
		}
		else if(addressDto.getRegion()!=null) {
			cityEntity.setState(addressDto.getRegion());
		}else {
			cityEntity.setState(NA);
		}
	}
	
	private void performCounty(CityEntity cityEntity,AddressDto addressDto) {
		if(addressDto.getCounty()!=null){
			cityEntity.setCounty(addressDto.getCounty());
		}
		else {
			cityEntity.setCounty(NA);
		}
	}
}
