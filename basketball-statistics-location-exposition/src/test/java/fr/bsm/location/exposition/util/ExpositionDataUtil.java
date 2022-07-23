package fr.bsm.location.exposition.util;

import fr.bsm.location.exposition.dto.ContinentDto;
import fr.bsm.location.exposition.dto.CountryDto;
import fr.bsm.location.exposition.dto.RegionDto;

public class ExpositionDataUtil {

	private static final Integer REGION_WESTERN_EUROPE_ID = 10;
	private static final String REGION_WESTERN_EUROPE_NAME = "Western Europe";

	private static final Integer CONTINENT_EUROPE_ID = 20;
	private static final String CONTINENT_EUROPE_NAME = "Europe";
	private static final String CONTINENT_EUROPE_CODE = "EU";

	private static final Integer COUNTRY_BELGIUM_ID = 30;
	private static final String COUNTRY_BELGIUM_NAME = "Belgium";
	private static final String COUNTRY_BELGIUM_CODE_ISO2 = "BE";
	private static final String COUNTRY_BELGIUM_CODE_ISO3 = "BEL";
	private static final String COUNTRY_BELGIUM_NUMBER = "123";
	private static final String COUNTRY_BELGIUM_FULLNAME = "Belgium Kingdom";


	public static RegionDto getRegionWesternEurope() {
		RegionDto getRegionDto = new RegionDto();
		getRegionDto.setId(REGION_WESTERN_EUROPE_ID);
		getRegionDto.setName(REGION_WESTERN_EUROPE_NAME);
		return getRegionDto;
	}

	public static ContinentDto getContinentEurope() {
		ContinentDto continentDto = new ContinentDto();
		continentDto.setId(CONTINENT_EUROPE_ID);
		continentDto.setName(CONTINENT_EUROPE_NAME);
		continentDto.setCode(CONTINENT_EUROPE_CODE);
		return continentDto;
	}

	public static CountryDto getCountryBelgium() {
		CountryDto countryDto = new CountryDto();
		countryDto.setId(COUNTRY_BELGIUM_ID);
		countryDto.setName(COUNTRY_BELGIUM_NAME);
		countryDto.setFullname(COUNTRY_BELGIUM_FULLNAME);
		countryDto.setNumber(COUNTRY_BELGIUM_NUMBER);
		countryDto.setCodeiso2(COUNTRY_BELGIUM_CODE_ISO2);
		countryDto.setCodeiso3(COUNTRY_BELGIUM_CODE_ISO3);
		countryDto.setRegion(getRegionWesternEurope());
		countryDto.setContinent(getContinentEurope());
		return countryDto;
	}

}
