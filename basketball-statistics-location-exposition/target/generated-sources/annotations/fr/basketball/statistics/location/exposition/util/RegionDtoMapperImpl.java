package fr.basketball.statistics.location.exposition.util;

import fr.basketball.statistics.location.domain.common.entity.region.RegionEntity;
import fr.basketball.statistics.location.domain.common.entity.region.RegionsEntity;
import fr.basketball.statistics.location.exposition.dto.RegionDto;
import fr.basketball.statistics.location.exposition.dto.RegionsDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-20T11:10:56+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.15 (Oracle Corporation)"
)
@Component
public class RegionDtoMapperImpl implements RegionDtoMapper {

    @Override
    public RegionDto entityToDto(RegionEntity regionEntity) {
        if ( regionEntity == null ) {
            return null;
        }

        RegionDto regionDto = new RegionDto();

        regionDto.setId( regionEntity.getId() );
        regionDto.setName( regionEntity.getName() );

        return regionDto;
    }

    @Override
    public RegionsDto entityToRegionsDto(RegionsEntity regionsEntity) {
        if ( regionsEntity == null ) {
            return null;
        }

        RegionsDto regionsDto = new RegionsDto();

        regionsDto.setItems( regionEntityListToRegionDtoList( regionsEntity.getItems() ) );

        return regionsDto;
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
