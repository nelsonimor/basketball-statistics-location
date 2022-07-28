package fr.bsm.location.exposition.util;

import fr.bsm.location.domain.common.entity.city.CityEntity;
import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.common.entity.region.RegionEntity;
import fr.bsm.location.exposition.dto.CityDto;
import fr.bsm.location.exposition.dto.CityRequestDto;
import fr.bsm.location.exposition.dto.ContinentDto;
import fr.bsm.location.exposition.dto.CountryDto;
import fr.bsm.location.exposition.dto.RegionDto;

public class ExpositionDataUtil {

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
	public static final String CITY_BRUSSELS_STATE = "Region de Bruxelles-Capitale";
	public static final String CITY_BRUSSELS_COUNTY = "Bruxelles Capitale";


	public static RegionDto getDtoRegionWesternEurope() {
		RegionDto getRegionDto = new RegionDto();
		getRegionDto.setId(REGION_WESTERN_EUROPE_ID);
		getRegionDto.setName(REGION_WESTERN_EUROPE_NAME);
		return getRegionDto;
	}

	public static ContinentDto getDtoContinentEurope() {
		ContinentDto continentDto = new ContinentDto();
		continentDto.setId(CONTINENT_EUROPE_ID);
		continentDto.setName(CONTINENT_EUROPE_NAME);
		continentDto.setCode(CONTINENT_EUROPE_CODE);
		return continentDto;
	}

	public static CountryDto getDtoCountryBelgium() {
		CountryDto countryDto = new CountryDto();
		countryDto.setId(COUNTRY_BELGIUM_ID);
		countryDto.setName(COUNTRY_BELGIUM_NAME);
		countryDto.setFullname(COUNTRY_BELGIUM_FULLNAME);
		countryDto.setNumber(COUNTRY_BELGIUM_NUMBER);
		countryDto.setCodeiso2(COUNTRY_BELGIUM_CODE_ISO2);
		countryDto.setCodeiso3(COUNTRY_BELGIUM_CODE_ISO3);
		countryDto.setRegion(getDtoRegionWesternEurope());
		countryDto.setContinent(getDtoContinentEurope());
		return countryDto;
	}
	
	public static RegionEntity getEntityRegionWesternEurope() {
		RegionEntity regionEntity = new RegionEntity();
		regionEntity.setId(REGION_WESTERN_EUROPE_ID);
		regionEntity.setName(REGION_WESTERN_EUROPE_NAME);
		return regionEntity;
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
	
	public static ContinentEntity getEntityContinentEurope() {
		ContinentEntity continentEntity = new ContinentEntity();
		continentEntity.setId(CONTINENT_EUROPE_ID);
		continentEntity.setName(CONTINENT_EUROPE_NAME);
		continentEntity.setCode(CONTINENT_EUROPE_CODE);
		return continentEntity;
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
	
	public static CityEntity getEntityCityBrusselsWithoutGeocoding() {
		CityEntity cityEntity = new CityEntity();
		cityEntity.setName(CITY_BRUSSELS_NAME);
		cityEntity.setCountry(getEntityCountryBelgium());
		return cityEntity;
	}
	
	
	public static CityDto getDtoCityBrussels() {
		CityDto cityDto = new CityDto();
		cityDto.setId(CITY_BRUSSELS_ID);
		cityDto.setName(CITY_BRUSSELS_NAME);
		cityDto.setCounty(CITY_BRUSSELS_COUNTY);
		cityDto.setLatitude(CITY_BRUSSELS_LATITUDE);
		cityDto.setLongitude(CITY_BRUSSELS_LONGITUDE);
		cityDto.setState(CITY_BRUSSELS_STATE);
		cityDto.setCountry(getDtoCountryBelgium());
		return cityDto;
	}
	
	public static CityRequestDto getDtoCityRequestBrussels() {
		CityRequestDto cityRequestDto = new CityRequestDto();
		cityRequestDto.setName(CITY_BRUSSELS_NAME);
		cityRequestDto.setCountryname(COUNTRY_BELGIUM_NAME);
		return cityRequestDto;
	}
}
