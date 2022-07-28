package fr.bsm.location.infrastructure.data.city;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.bsm.location.infrastructure.data.CoreData;
import fr.bsm.location.infrastructure.data.country.CountryData;
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
@Table(name = "t_city")
public class CityData extends CoreData {

	@NotNull
	private String name;

	@NotNull
	private Double longitude;

	@NotNull
	private Double latitude;

	@NotNull
	private String state;

	@NotNull
	private String county;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_country_id",nullable = false)
	@NotNull
	private CountryData country;


	@Builder
	public CityData(Integer id,Timestamp creationDate,Timestamp updateDate,String name,Double longitude,Double latitude,String state,String county,CountryData country) {
		super(id,creationDate,updateDate);
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.state = state;
		this.county = county;
		this.country = country;
	}
	



}
