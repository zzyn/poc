package com.webflux.cassandra.demo.service;


import com.webflux.cassandra.demo.domain.dto.PlanDto;
import com.webflux.cassandra.demo.domain.entity.PlanEntity;
import com.webflux.cassandra.demo.domain.model.PlanQueryModel;
import org.springframework.data.cassandra.core.InsertOptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * PlanService interface
 *
 * @author ef edtech
 */
public interface PlanService {

    /**
     * create plan
     *
     * @param  plan plan entity
     * @param  options insert option
     * @return Mono<PlanDto> return value
     * @throws Exception exception
     */
    Mono<PlanDto> create(PlanEntity plan, InsertOptions options);

    /**
     * update plan
     *
     * @param  plan PlanEntity
     * @return PlanDto return value
     */
    Mono<Boolean> update(PlanEntity plan);

    /**
     * create plan in batch
     *
     * @param plans List<PlanEntity>
     * @param options InsertOptions
     * @return List<PlanDto>
     * @throws Exception exception
     */
    Mono<List<PlanDto>> createBatch(List<PlanEntity> plans, InsertOptions options);

    /**
     * delete plan
     *
     * @param query PlanQueryModel
     * @return Mono<Boolean>
     * @throws Exception throw exception
     */
    Mono<Boolean> detete(PlanQueryModel query);

    /**
     * get plan
     *
     * @param  query PlanQueryModel
     * @param  limit int You want the number of rows
     * @return Flux<PlanDto>
     * @param page pageIndex
     * @throws Exception throw exception
     */
    Flux<PlanDto> getPlans (PlanQueryModel query, Integer limit,Integer page);

}
