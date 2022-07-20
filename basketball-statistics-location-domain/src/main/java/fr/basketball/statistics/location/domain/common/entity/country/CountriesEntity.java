package fr.basketball.statistics.location.domain.common.entity.country;
import java.util.List;

import fr.basketball.statistics.location.domain.ddd.DDD;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@DDD.AggregateRoot
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountriesEntity {
	
	private List<CountryEntity> items;
	
}

