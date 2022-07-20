package fr.bsm.location.domain.common.entity.continent;

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
public class ContinentEntity {

  /**
   * Entity object Id
   */
  @NotNull
  @EqualsAndHashCode.Include
  private Integer id;
  
  @NotNull
  private String code;
  
  @NotNull
  private String name;


}
