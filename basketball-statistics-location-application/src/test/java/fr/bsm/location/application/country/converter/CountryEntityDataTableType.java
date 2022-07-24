package fr.bsm.location.application.country.converter;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.country.CountryEntity;
import io.cucumber.java.DataTableType;

public class CountryEntityDataTableType {

  private final ObjectMapper objectMapper;

  public CountryEntityDataTableType() {
    objectMapper = new ObjectMapper();
    objectMapper.findAndRegisterModules();
  }

  @DataTableType
  public CountryEntity define(Map<String, String> entry) {
    return objectMapper.convertValue(entry, CountryEntity.class);
  }
}