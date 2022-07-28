package fr.bsm.location.infrastructure.data.geocoding;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
	
	private String city;
	private String village;
	private String country;
	private String state;
	private String county;
	private String postcode;
	private String region;

}
