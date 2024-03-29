package fr.bsm.location.domain.util;

import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.common.entity.region.RegionEntity;

public class DomainDataUtil {

	public static final Integer REGION_WESTERN_EUROPE_ID = 10;
	public static final String REGION_WESTERN_EUROPE_NAME = "Western Europe";

	public static final Integer CONTINENT_EUROPE_ID = 20;
	public static final String CONTINENT_EUROPE_NAME = "Europe";
	public static final String CONTINENT_EUROPE_CODE = "EU";

	public static final Integer COUNTRY_BELGIUM_ID = 30;
	public static final String COUNTRY_BELGIUM_NAME = "Belgium";
	public static final String COUNTRY_BELGIUM_CODE_ISO2 = "BE";
	public static final String COUNTRY_BELGIUM_CODE_ISO3 = "BEL";
	public static final String COUNTRY_BELGIUM_NUMBER = "123";
	public static final String COUNTRY_BELGIUM_FULLNAME = "Belgium Kingdom";
	
	private static final Integer CITY_BRUSSELS_ID = 40;
	private static final String CITY_BRUSSELS_NAME = "Brussels";
	private static final Double CITY_BRUSSELS_LONGITUDE = 2.452367;
	private static final Double CITY_BRUSSELS_LATITUDE = 40.1245;
	private static final String CITY_BRUSSELS_STATE = "Région de Bruxelles-Capitale";
	private static final String CITY_BRUSSELS_COUNTY = "Bruxelles-Capitale";


	public static RegionEntity getRegionWesternEurope() {
		RegionEntity regionEntity = new RegionEntity();
		regionEntity.setId(REGION_WESTERN_EUROPE_ID);
		regionEntity.setName(REGION_WESTERN_EUROPE_NAME);
		return regionEntity;
	}

	public static ContinentEntity getContinentEurope() {
		ContinentEntity continentEntity = new ContinentEntity();
		continentEntity.setId(CONTINENT_EUROPE_ID);
		continentEntity.setName(CONTINENT_EUROPE_NAME);
		continentEntity.setCode(CONTINENT_EUROPE_CODE);
		return continentEntity;
	}

	public static CountryEntity getCountryBelgium() {
		CountryEntity countryEntity = new CountryEntity();
		countryEntity.setId(COUNTRY_BELGIUM_ID);
		countryEntity.setName(COUNTRY_BELGIUM_NAME);
		countryEntity.setFullname(COUNTRY_BELGIUM_FULLNAME);
		countryEntity.setNumber(COUNTRY_BELGIUM_NUMBER);
		countryEntity.setCodeiso2(COUNTRY_BELGIUM_CODE_ISO2);
		countryEntity.setCodeiso3(COUNTRY_BELGIUM_CODE_ISO3);
		countryEntity.setRegion(getRegionWesternEurope());
		countryEntity.setContinent(getContinentEurope());
		return countryEntity;
	}
	
	public static CityEntity getCityBrussels() {
		CityEntity cityEntity = new CityEntity();
		cityEntity.setId(CITY_BRUSSELS_ID);
		cityEntity.setName(CITY_BRUSSELS_NAME);
		cityEntity.setCounty(CITY_BRUSSELS_COUNTY);
		cityEntity.setLatitude(CITY_BRUSSELS_LATITUDE);
		cityEntity.setLongitude(CITY_BRUSSELS_LONGITUDE);
		cityEntity.setState(CITY_BRUSSELS_STATE);
		cityEntity.setCountry(getCountryBelgium());
		return cityEntity;
	}

}
