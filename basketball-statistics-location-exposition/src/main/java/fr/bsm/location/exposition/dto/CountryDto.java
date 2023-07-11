package fr.bsm.location.exposition.dto;

import javax.validation.constraints.NotNull;

import fr.bsm.location.domain.ddd.DDD;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@DDD.Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {

  /**
   * Entity object Id
   */
  @NotNull
  private Integer id;
   
  @NotNull
  private String name;
  
  @NotNull
  private String fullname;
  
  @NotNull
  private String codeiso2;
  
  @NotNull
  private String codeiso3;
  
  @NotNull
  private String number;
  
  @NotNull
  private String flagurl;
  
  @NotNull
  private ContinentDto continent;
  
  @NotNull
  private RegionDto region;


}
