package fr.basketball.statistics.location.exposition.util;

import fr.basketball.statistics.location.domain.common.entity.RegionEntity;
import fr.basketball.statistics.location.domain.common.entity.RegionsEntity;
import fr.basketball.statistics.location.exposition.dto.RegionDto;
import fr.basketball.statistics.location.exposition.dto.RegionDto.RegionDtoBuilder;
import fr.basketball.statistics.location.exposition.dto.RegionsDto;
import fr.basketball.statistics.location.exposition.dto.RegionsDto.RegionsDtoBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-10T20:20:46+0200",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.4.0.v20210708-0430, environment: Java 17 (Eclipse Adoptium)"
)
@Component
public class RegionDtoMapperImpl implements RegionDtoMapper {

    @Override
    public RegionDto entityToDto(RegionEntity regionEntity) {
        if ( regionEntity == null ) {
            return null;
        }

        RegionDtoBuilder regionDto = RegionDto.builder();

        regionDto.id( regionEntity.getId() );
        regionDto.name( regionEntity.getName() );

        return regionDto.build();
    }

    @Override
    public RegionsDto entityToRegionsDto(RegionsEntity regionsEntity) {
        if ( regionsEntity == null ) {
            return null;
        }

        RegionsDtoBuilder regionsDto = RegionsDto.builder();

        regionsDto.items( regionEntityListToRegionDtoList( regionsEntity.getItems() ) );

        return regionsDto.build();
    }

    protected List<RegionDto> regionEntityListToRegionDtoList(List<RegionEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<RegionDto> list1 = new ArrayList<RegionDto>( list.size() );
        for ( RegionEntity regionEntity : list ) {
            list1.add( entityToDto( regionEntity ) );
        }

        return list1;
    }
}
