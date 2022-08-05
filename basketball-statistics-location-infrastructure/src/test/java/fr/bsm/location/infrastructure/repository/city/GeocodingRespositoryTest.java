package fr.bsm.location.infrastructure.repository.city;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.repository.city.GeocodingRepository;
import fr.bsm.location.infrastructure.config.GeocodingProperties;
import fr.bsm.location.infrastructure.data.geocoding.AddressDto;
import fr.bsm.location.infrastructure.data.geocoding.AddressResultDto;
import fr.bsm.location.infrastructure.util.InfrastructureDataUtil;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

class GeocodingRespositoryTest {


	GeocodingRepository geocodingRepository;

	private MockWebServer server;
	
	private GeocodingProperties geocodingProperties;


	@BeforeEach
	public void setUp() throws IOException {
		
		geocodingProperties = new GeocodingProperties();
		geocodingProperties.setAcceptlanguage("en");
		geocodingProperties.setUrl("https://nominatim.openstreetmap.org");
		geocodingProperties.setAddressdetails(2);
		geocodingProperties.setFormat("json");
		geocodingProperties.setLimit(1);
		
		server = new MockWebServer();
		server.start();
		geocodingRepository = new GeocodingRepositoryImpl(geocodingProperties);
	}

	@AfterEach
	public void tearDown() throws IOException {
		server.shutdown();
	}


	@Test
	void geocodingOk() throws IOException {

		AddressDto addressDto = new AddressDto();
		addressDto.setCity(InfrastructureDataUtil.CITY_GAND_NAME);

		AddressResultDto addressResultDto = new AddressResultDto();
		addressResultDto.setAddress(addressDto);
		addressResultDto.setLon(InfrastructureDataUtil.CITY_GAND_LONGITUDE);
		addressResultDto.setLat(InfrastructureDataUtil.CITY_GAND_LATITUDE);

		ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);
		String responseBody = objectMapper.writeValueAsString(addressResultDto);

		MockResponse response = new MockResponse().addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).setBody(responseBody);
		server.enqueue(response);

		Optional<CityEntity> cityEntityGeocoded = geocodingRepository.geocode(InfrastructureDataUtil.getEntityCityGandWithoutGeocoding());

		assertThat(cityEntityGeocoded).isNotNull();
		assertThat(cityEntityGeocoded.get().getName()).isEqualTo(InfrastructureDataUtil.CITY_GAND_NAME);
		assertThat(cityEntityGeocoded.get().getLongitude()).isEqualTo(InfrastructureDataUtil.CITY_GAND_LONGITUDE);
		assertThat(cityEntityGeocoded.get().getLatitude()).isEqualTo(InfrastructureDataUtil.CITY_GAND_LATITUDE);
		assertThat(cityEntityGeocoded.get().getState()).isEqualTo(InfrastructureDataUtil.CITY_GAND_STATE);
		assertThat(cityEntityGeocoded.get().getCounty()).isEqualTo(InfrastructureDataUtil.CITY_GAND_COUNTY);
	}

}
