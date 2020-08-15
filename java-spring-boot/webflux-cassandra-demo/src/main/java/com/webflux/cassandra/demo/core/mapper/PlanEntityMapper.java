package com.webflux.cassandra.demo.core.mapper;




import com.webflux.cassandra.demo.core.utility.PlanUtility;
import com.webflux.cassandra.demo.domain.entity.PlanEntity;
import com.webflux.cassandra.demo.domain.model.PlanRequestModel;
import com.webflux.cassandra.demo.domain.model.PlanUpdateRequestModel;
import org.mapstruct.*;

import java.util.List;

/**
 * PlanEntityMapper class
 *
 * @author ef edtech
 */
@Mapper(imports = { PlanUtility.class, com.datastax.driver.core.utils.UUIDs.class})
public interface PlanEntityMapper {

    /**
     * convert planRequestModel to PlanEntity
     *
     * @param planRequest PlanRequestModel
     * @return PlanEntity plan entity
     * @throws Exception exception
     */
//    @Mapping(target = "createdTime",  expression = "java(PlanUtility.toTimeStamp(planRequest.getCreatedTime()))")
//    @Mapping(target = "lastUpdatedTime", expression = "java(PlanUtility.toTimeStamp(planRequest.getLastupdatedTime()))")
    @Mapping(target = "systemKey",expression = "java(UUIDs.timeBased())")
//    @Mapping(target = "startTime", expression = "java(PlanUtility.toTimeStamp(planRequest.getStartTime()))")
//    @Mapping(target = "endTime", expression = "java(PlanUtility.toTimeStamp(planRequest.getEndTime()))")
    PlanEntity planRequestToPlanEntity(PlanRequestModel planRequest);


    /**
     * convert planRequestModel collection to PlanEntity collection
     *
     * @param planRequestModels PlanRequestModel collection
     * @return PlanEntity return value
     * @throws Exception exception
     */
    @IterableMapping(elementTargetType = PlanEntity.class)
    List<PlanEntity> planRequestToPlanEntity(List<PlanRequestModel> planRequestModels);

    /**
     * convert planRequestModel to PlanEntity
     *
     * @param planUpdateRequest PlanUpdateRequestModel
     * @return PlanEntity plan entity
     * @throws Exception exception
     */
//    @Mapping(target = "createdTime",  expression = "java(PlanUtility.toTimeStamp(planUpdateRequest.getCreatedTime()))")
//    @Mapping(target = "lastUpdatedTime", expression = "java(PlanUtility.toTimeStamp(planUpdateRequest.getLastupdatedTime()))")
//    @Mapping(target = "startTime", expression = "java(PlanUtility.toTimeStamp(planUpdateRequest.getStartTime()))")
//    @Mapping(target = "endTime", expression = "java(PlanUtility.toTimeStamp(planUpdateRequest.getEndTime()))")
    PlanEntity planUpdateRequestToPlanEntity(PlanUpdateRequestModel planUpdateRequest);

}
