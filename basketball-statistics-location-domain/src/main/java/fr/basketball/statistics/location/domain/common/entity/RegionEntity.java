package fr.basketball.statistics.location.domain.common.entity;

import java.time.Instant;

import javax.validation.constraints.NotNull;


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
public class RegionEntity {

  /**
   * Entity object Id
   */
  @NotNull
  private Integer id;
  
  @NotNull
  private String name;


}
