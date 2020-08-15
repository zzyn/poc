package com.webflux.cassandra.demo.core.mapper;

import com.webflux.cassandra.demo.domain.model.PlanRequestModel;
import com.webflux.cassandra.demo.domain.model.PlanUpdateRequestModel;
import org.mapstruct.Mapper;

/**
 * PlanUpdateRequestMapper interface
 *
 * @author ef edtech
 */
@Mapper
public interface PlanUpdateRequestMapper {
    /**
     * convert PlanRequestModel to PlanUpdateRequestModel
     *
     * @param planRequestModel PlanRequestModel
     * @return PlanUpdateRequestModel
     */
    PlanUpdateRequestModel planRequestToPlanUpdateRequest(PlanRequestModel planRequestModel);
}
