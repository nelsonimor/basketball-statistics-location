package fr.basketball.statistics.location.application.continent.converter;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import io.cucumber.java.DataTableType;

public class ContinentEntityDataTableType {

  private final ObjectMapper objectMapper;

  public ContinentEntityDataTableType() {
    objectMapper = new ObjectMapper();
    objectMapper.findAndRegisterModules();
  }

  @DataTableType
  public ContinentEntity define(Map<String, String> entry) {
    return objectMapper.convertValue(entry, ContinentEntity.class);
  }
}