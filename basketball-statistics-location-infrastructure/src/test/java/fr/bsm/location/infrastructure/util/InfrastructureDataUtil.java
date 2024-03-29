package fr.bsm.location.infrastructure.util;

import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.common.entity.region.RegionEntity;
import fr.bsm.location.infrastructure.data.city.CityData;
import fr.bsm.location.infrastructure.data.continent.ContinentData;
import fr.bsm.location.infrastructure.data.country.CountryData;
import fr.bsm.location.infrastructure.data.region.RegionData;

public class InfrastructureDataUtil {
	
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
	
	public static final Integer CITY_BRUSSELS_ID = 40;
	public static final String CITY_BRUSSELS_NAME = "Brussels";
	public static final Double CITY_BRUSSELS_LONGITUDE = 2.452367;
	public static final Double CITY_BRUSSELS_LATITUDE = 40.1245;
	public static final String CITY_BRUSSELS_STATE = "Région de Bruxelles-Capitale";
	public static final String CITY_BRUSSELS_COUNTY = "Bruxelles-Capitale";
	
	public static final Integer CITY_GAND_ID = 50;
	public static final String CITY_GAND_NAME = "Gand";
	public static final Double CITY_GAND_LONGITUDE = 3.7250121;
	public static final Double CITY_GAND_LATITUDE = 51.0538286;
	public static final String CITY_GAND_STATE = "East Flanders";
	public static final String CITY_GAND_COUNTY = "Gent";
	
	
	public static RegionEntity getEntityRegionWesternEurope() {
		RegionEntity regionEntity = new RegionEntity();
		regionEntity.setId(REGION_WESTERN_EUROPE_ID);
		regionEntity.setName(REGION_WESTERN_EUROPE_NAME);
		return regionEntity;
	}
	
	public static RegionData getDataRegionWesternEurope() {
		RegionData regionData = new RegionData();
		regionData.setId(REGION_WESTERN_EUROPE_ID);
		regionData.setName(REGION_WESTERN_EUROPE_NAME);
		return regionData;
	}
	
	
	public static ContinentEntity getEntityContinentEurope() {
		ContinentEntity continentEntity = new ContinentEntity();
		continentEntity.setId(CONTINENT_EUROPE_ID);
		continentEntity.setName(CONTINENT_EUROPE_NAME);
		continentEntity.setCode(CONTINENT_EUROPE_CODE);
		return continentEntity;
	}
	
	public static ContinentData getDataContinentEurope() {
		ContinentData continentData = new ContinentData();
		continentData.setId(CONTINENT_EUROPE_ID);
		continentData.setName(CONTINENT_EUROPE_NAME);
		continentData.setCode(CONTINENT_EUROPE_CODE);
		return continentData;
	}
	
	public static CountryEntity getEntityCountryBelgium() {
		CountryEntity countryEntity = new CountryEntity();
		countryEntity.setId(COUNTRY_BELGIUM_ID);
		countryEntity.setName(COUNTRY_BELGIUM_NAME);
		countryEntity.setFullname(COUNTRY_BELGIUM_FULLNAME);
		countryEntity.setNumber(COUNTRY_BELGIUM_NUMBER);
		countryEntity.setCodeiso2(COUNTRY_BELGIUM_CODE_ISO2);
		countryEntity.setCodeiso3(COUNTRY_BELGIUM_CODE_ISO3);
		countryEntity.setRegion(getEntityRegionWesternEurope());
		countryEntity.setContinent(getEntityContinentEurope());
		return countryEntity;
	}
	
	public static CountryData getDataCountryBelgium() {
		CountryData countryData = new CountryData();
		countryData.setId(COUNTRY_BELGIUM_ID);
		countryData.setName(COUNTRY_BELGIUM_NAME);
		countryData.setFullname(COUNTRY_BELGIUM_FULLNAME);
		countryData.setNumber(COUNTRY_BELGIUM_NUMBER);
		countryData.setCodeiso2(COUNTRY_BELGIUM_CODE_ISO2);
		countryData.setCodeiso3(COUNTRY_BELGIUM_CODE_ISO3);
		countryData.setRegion(getDataRegionWesternEurope());
		countryData.setContinent(getDataContinentEurope());
		return countryData;
	}
	
	public static CityEntity getEntityCityBrussels() {
		CityEntity cityEntity = new CityEntity();
		cityEntity.setId(CITY_BRUSSELS_ID);
		cityEntity.setName(CITY_BRUSSELS_NAME);
		cityEntity.setCounty(CITY_BRUSSELS_COUNTY);
		cityEntity.setLatitude(CITY_BRUSSELS_LATITUDE);
		cityEntity.setLongitude(CITY_BRUSSELS_LONGITUDE);
		cityEntity.setState(CITY_BRUSSELS_STATE);
		cityEntity.setCountry(getEntityCountryBelgium());
		return cityEntity;
	}
	
	public static CityEntity getEntityCityGand() {
		CityEntity cityEntity = new CityEntity();
		cityEntity.setId(CITY_GAND_ID);
		cityEntity.setName(CITY_GAND_NAME);
		cityEntity.setCounty(CITY_GAND_COUNTY);
		cityEntity.setLatitude(CITY_GAND_LATITUDE);
		cityEntity.setLongitude(CITY_GAND_LONGITUDE);
		cityEntity.setState(CITY_GAND_STATE);
		cityEntity.setCountry(getEntityCountryBelgium());
		return cityEntity;
	}
	
	public static CityEntity getEntityCityGandWithoutGeocoding() {
		CityEntity cityEntity = new CityEntity();
		cityEntity.setName(CITY_GAND_NAME);
		cityEntity.setCountry(getEntityCountryBelgium());
		return cityEntity;
	}
	
	public static CityData getDataCityGand() {
		CityData cityData = new CityData();
		cityData.setId(CITY_GAND_ID);
		cityData.setName(CITY_GAND_NAME);
		cityData.setCounty(CITY_GAND_COUNTY);
		cityData.setLatitude(CITY_GAND_LATITUDE);
		cityData.setLongitude(CITY_GAND_LONGITUDE);
		cityData.setState(CITY_GAND_STATE);
		cityData.setCountry(getDataCountryBelgium());
		return cityData;
	}
	
	public static CityData getDataCityBrussels() {
		CityData cityData = new CityData();
		cityData.setId(CITY_BRUSSELS_ID);
		cityData.setName(CITY_BRUSSELS_NAME);
		cityData.setCounty(CITY_BRUSSELS_COUNTY);
		cityData.setLatitude(CITY_BRUSSELS_LATITUDE);
		cityData.setLongitude(CITY_BRUSSELS_LONGITUDE);
		cityData.setState(CITY_BRUSSELS_STATE);
		cityData.setCountry(getDataCountryBelgium());
		return cityData;
	}

}
