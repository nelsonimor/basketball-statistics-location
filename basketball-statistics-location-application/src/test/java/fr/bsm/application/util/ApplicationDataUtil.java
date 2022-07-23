package fr.bsm.application.util;

import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import fr.bsm.location.domain.common.entity.region.RegionEntity;

public class ApplicationDataUtil {

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

}
