package fr.bsm.location.infrastructure.data.country;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.bsm.location.infrastructure.data.CoreData;
import fr.bsm.location.infrastructure.data.continent.ContinentData;
import fr.bsm.location.infrastructure.data.region.RegionData;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
	
	@Builder
	public CountryData(Integer id,Timestamp creationDate,Timestamp updateDate,String name,String fullname,String codeiso2,String codeiso3,String number,ContinentData continent,RegionData region) {
		super(id,creationDate,updateDate);
		this.codeiso2 = codeiso2;
		this.codeiso3 = codeiso3;
		this.name = name;
		this.continent = continent;
		this.region = region;
		this.number = number;
		this.fullname = fullname;
	}


}
