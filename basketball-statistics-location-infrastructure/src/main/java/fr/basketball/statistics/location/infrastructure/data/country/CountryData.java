package fr.basketball.statistics.location.infrastructure.data.country;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.basketball.statistics.location.infrastructure.data.CoreData;
import fr.basketball.statistics.location.infrastructure.data.continent.ContinentData;
import fr.basketball.statistics.location.infrastructure.data.region.RegionData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_country")
public class CountryData extends CoreData {

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

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_continent_id",nullable = false)
	@NotNull
	private ContinentData continent;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_region_id",nullable = false)
	@NotNull
	private RegionData region;


}
