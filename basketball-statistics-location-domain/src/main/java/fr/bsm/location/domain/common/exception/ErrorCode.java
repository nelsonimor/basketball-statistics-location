package fr.bsm.location.domain.common.exception;

public class ErrorCode {
	
	private ErrorCode() {}
	
	//city
	public static final String GET_CITY_UNKNOWN_BY_ID = "0001";
	public static final String DELETE_CITY_UNKNOWN_BY_ID = "0002";
	public static final String ADD_CITY_COUNTRY_NOT_FOUND = "0003";
	public static final String ADD_CITY_ALREADY_EXISTS = "0004";
	public static final String ADD_CITY_NO_GEOCODING = "0005";

	//continent
	public static final String GET_CONTINENT_UNKNOWN_BY_ID = "0020";
	
	//region
	public static final String GET_REGION_UNKNOWN_BY_ID = "0030";
	
	//country
	public static final String GET_COUNTRY_UNKNOWN_BY_ID = "0040";
	public static final String GET_COUNTRY_UNKNOWN_BY_NAME = "0041";
	public static final String GET_COUNTRY_UNKNOWN_BY_ISO2 = "0042";
	public static final String GET_COUNTRY_UNKNOWN_BY_ISO3 = "0043";
}
