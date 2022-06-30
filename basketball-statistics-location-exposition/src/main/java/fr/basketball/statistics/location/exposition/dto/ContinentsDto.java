package fr.basketball.statistics.location.exposition.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ContinentsDto {

  @NotNull
  private List<ContinentDto> items;
}
