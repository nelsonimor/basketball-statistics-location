package fr.bsm.location.infrastructure.repository.city;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.repository.city.GeocodingRepository;
import fr.bsm.location.infrastructure.config.SpringProfileConstants;
import fr.bsm.location.infrastructure.data.geocoding.AddressDto;
import fr.bsm.location.infrastructure.data.geocoding.AddressResultDto;


@SpringBootTest(classes = {GeocodingRepositoryMock.class})
@ActiveProfiles(SpringProfileConstants.MOCK_GEOCODING)
class GeocodingRepositoryMockTest {
	
	private final static String STEENE = "Steene";
	
	private final static String PARIS = "Paris";

	@MockBean
	ObjectMapper objectMapper;

	@Autowired
	GeocodingRepository geocodingRepository;


	@Test
	void testGeocoding_ResultIsNotPresent() throws IOException {
		var geocodingResponse = createResponse(STEENE);
		when(objectMapper.readValue(any(InputStream.class), eq(AddressResultDto[].class))).thenReturn(geocodingResponse);
		
		var geocodingResult = geocodingRepository.geocode(CityEntity.builder().name(PARIS).build());
		assertThat(geocodingResult).isEmpty();
	}

	@Test
	void testGeocoding_ResultIsPresent() throws IOException {
		var geocodingResponse = createResponse(STEENE);
		when(objectMapper.readValue(any(InputStream.class), eq(AddressResultDto[].class))).thenReturn(geocodingResponse);

		var geocodingResult = geocodingRepository.geocode(CityEntity.builder().name(STEENE).build());

		assertThat(geocodingResult).isNotEmpty();
		var cityNameFound = geocodingResult.get().getName();
		assertThat(cityNameFound).isEqualTo(STEENE);
	}

	public static AddressResultDto[] createResponse(String cityName) {

		AddressResultDto[] result = new AddressResultDto[1];

		AddressResultDto addressResultDto = new AddressResultDto();
		AddressDto addressDto = new AddressDto();
		addressDto.setCity(STEENE);
		addressResultDto.setAddress(addressDto);

		result[0] = addressResultDto;
		return result;
	}


}
