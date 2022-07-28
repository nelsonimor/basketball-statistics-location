package fr.bsm.location.application.city;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.bsm.location.domain.common.entity.city.CityEntity;
import io.cucumber.java.DataTableType;

public class CityEntityDataTableType {

  private final ObjectMapper objectMapper;

  public CityEntityDataTableType() {
    objectMapper = new ObjectMapper();
    objectMapper.findAndRegisterModules();
  }

  @DataTableType
  public CityEntity define(Map<String, String> entry) {
    return objectMapper.convertValue(entry, CityEntity.class);
  }
}