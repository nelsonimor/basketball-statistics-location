package fr.basketball.statistics.location.domain.common.entity.country;

import javax.validation.constraints.NotNull;

import fr.basketball.statistics.location.domain.common.entity.continent.ContinentEntity;
import fr.basketball.statistics.location.domain.common.entity.region.RegionEntity;
import fr.basketball.statistics.location.domain.ddd.DDD;
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
public class CountryEntity {

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
  private ContinentEntity continent;
  
  @NotNull
  private RegionEntity region;


}
