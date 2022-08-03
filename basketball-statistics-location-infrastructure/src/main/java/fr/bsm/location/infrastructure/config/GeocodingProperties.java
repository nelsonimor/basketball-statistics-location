package fr.bsm.location.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties("geocoding.openstreetmap")
public class GeocodingProperties {
	
	private String url;
	
	private Integer addressdetails;
	
	private String format;
	
	private Integer limit;
	
	private String acceptlanguage;

}
