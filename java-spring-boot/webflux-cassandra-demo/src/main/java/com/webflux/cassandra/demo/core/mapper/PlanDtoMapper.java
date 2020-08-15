package com.webflux.cassandra.demo.core.mapper;

import com.webflux.cassandra.demo.core.utility.PlanUtility;
import com.webflux.cassandra.demo.domain.dto.PlanDto;
import com.webflux.cassandra.demo.domain.entity.PlanEntity;
import com.webflux.cassandra.demo.domain.model.PlanUpdateRequestModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * PlanDtoMapper class
 *
 * @author ef edtech
 */
@Mapper(imports = { PlanUtility.class, com.datastax.driver.core.utils.UUIDs.class})
public interface PlanDtoMapper {

    /**
     * convert planEntity to planDto
     *
     * @param planEntity  PlanEntity
     * @return PlanDto
     */
    PlanDto planEntityToPlanDto(PlanEntity planEntity);

    /**
     * convert planUpdateRequestModel to planDto
     *
     * @param planEntity  PlanEntity
     * @return PlanDto
     */
    PlanDto planUpdateRequestModelToPlanDto(PlanUpdateRequestModel planEntity);

    /**
     * convert planEntity collection to planDto collection
     *
     * @param planEntityList List<PlanEntity>
     * @return PlanDto collection
     */
    @IterableMapping(elementTargetType = PlanDto.class)
    List<PlanDto> planRequestCollectionToPlanDto(List<PlanEntity> planEntityList);
}
