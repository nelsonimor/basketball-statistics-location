package fr.basketball.statistics.location.infrastructure.data.region;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.basketball.statistics.location.infrastructure.data.CoreData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_region")
public class RegionData extends CoreData {
	
	@NotNull
	private String name;
	
	@Builder
	public RegionData(Integer id,Timestamp creationDate,Timestamp updateDate,String name) {
		super(id,creationDate,updateDate);
		this.name = name;
	}
	
}
