package fr.basketball.statistics.location.infrastructure.data.continent;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_continent")
public class ContinentData extends CoreData {
	
	@NotNull
	private String code;
	
	@NotNull
	private String name;
	
	@Builder
	public ContinentData(Integer id,Timestamp creationDate,Timestamp updateDate,String code,String name) {
		super(id,creationDate,updateDate);
		this.code = code;
		this.name = name;
	}

}
