package fr.bsm.location.application.region;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.bsm.location.domain.common.entity.region.RegionEntity;
import io.cucumber.java.DataTableType;

public class RegionEntityDataTableType {

  private final ObjectMapper objectMapper;

  public RegionEntityDataTableType() {
    objectMapper = new ObjectMapper();
    objectMapper.findAndRegisterModules();
  }

  @DataTableType
  public RegionEntity define(Map<String, String> entry) {
    return objectMapper.convertValue(entry, RegionEntity.class);
  }
}