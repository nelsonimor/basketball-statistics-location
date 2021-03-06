package fr.bsm.location.domain.common.entity.continent;
import java.util.List;

import fr.bsm.location.domain.ddd.DDD;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@DDD.AggregateRoot
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContinentsEntity {
	
	private List<ContinentEntity> items;
	
}

