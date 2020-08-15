package com.webflux.cassandra.demo.core.mapper;

import org.mapstruct.factory.Mappers;

public class PlanMapperUtility {
    private PlanMapperUtility(){throw  new UnsupportedOperationException();}
    public static final PlanDtoMapper PLAN_DTO_MAPPER=Mappers.getMapper( PlanDtoMapper.class );
    public static final PlanEntityMapper PLAN_ENTITY_MAPPER= Mappers.getMapper( PlanEntityMapper.class );
    public static final PlanUpdateRequestMapper PLAN_UPDATE_REQUEST_MAPPER= Mappers.getMapper( PlanUpdateRequestMapper.class );

}
