package fr.basketball.statistics.location.infrastructure.util;

import fr.basketball.statistics.location.domain.common.entity.RegionEntity;
import fr.basketball.statistics.location.domain.common.entity.RegionEntity.RegionEntityBuilder;
import fr.basketball.statistics.location.infrastructure.data.RegionData;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-10T20:20:45+0200",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.4.0.v20210708-0430, environment: Java 17 (Eclipse Adoptium)"
)
@Component
public class RegionsEntityMapperImpl implements RegionsEntityMapper {

    @Override
    public RegionEntity dataToEntity(RegionData regionData) {
        if ( regionData == null ) {
            return null;
        }

        RegionEntityBuilder regionEntity = RegionEntity.builder();

        regionEntity.id( regionData.getId() );
        regionEntity.name( regionData.getName() );

        return regionEntity.build();
    }

    @Override
    public RegionData entityToData(RegionEntity regionEntity) {
        if ( regionEntity == null ) {
            return null;
        }

        RegionData regionData = new RegionData();

        regionData.setId( regionEntity.getId() );
        regionData.setName( regionEntity.getName() );

        return regionData;
    }
}
