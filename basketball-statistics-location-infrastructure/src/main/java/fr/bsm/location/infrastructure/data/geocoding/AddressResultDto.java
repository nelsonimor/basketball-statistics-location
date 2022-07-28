package fr.bsm.location.infrastructure.data.geocoding;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResultDto {
	
	private Double lon;
	private Double lat;
	private AddressDto address;
	

}
