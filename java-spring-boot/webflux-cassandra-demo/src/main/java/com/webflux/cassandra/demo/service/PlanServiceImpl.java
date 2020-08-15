package com.webflux.cassandra.demo.service;

import com.datastax.driver.core.ConsistencyLevel;
import com.webflux.cassandra.demo.core.utility.PlanUtility;
import com.webflux.cassandra.demo.domain.dto.PlanDto;
import com.webflux.cassandra.demo.domain.entity.PlanEntity;
import com.webflux.cassandra.demo.domain.model.PlanQueryModel;
import com.webflux.cassandra.demo.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * PlanServiceImpl class
 *
 * @author ef edtech
 */
@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    @Autowired
    public PlanServiceImpl(PlanRepository planService){
        this.planRepository=planService;
    }

    @Override
    public Mono<PlanDto> create(PlanEntity plan, InsertOptions options){
        return planRepository.insert(plan,options);
    }

    @Override
    public Mono<Boolean> update(PlanEntity plan) {
        return planRepository.update(plan);
    }

    @Override
    public Mono<List<PlanDto>> createBatch(List<PlanEntity> plans, InsertOptions options) {
        if (plans.size() > 1) {
            PlanEntity planCompare = plans.get(0);
            long planInSamePartition = 0L;
            for (PlanEntity planEntity : plans) {
                if (planEntity.getBucketId().equals(planCompare.getBucketId())
                        && planEntity.getPlanBusinessKey().equals(planCompare.getPlanBusinessKey())
                        && (planEntity.getProductId() == planCompare.getProductId())) {
                    planInSamePartition++;
                }
            }
            if (planInSamePartition != plans.size()) {
                return null;
            }
        }
        return planRepository.insertBatch(plans, options);
    }

    @Override
    public Mono<Boolean> detete(PlanQueryModel query){
        Boolean valid= PlanUtility.isValid(query);
        if (valid==null||!valid.booleanValue()) {
            return Mono.just(false);
        }
        return planRepository.detete(query, ConsistencyLevel.QUORUM);
    }

    @Override
    public Flux<PlanDto> getPlans(PlanQueryModel query, Integer limit,Integer page) {
        Boolean valid=PlanUtility.isValid(query);
        if (valid==null||!valid.booleanValue()) {
            return Flux.just();
        }
        return planRepository.queryByWhere(query,PlanUtility.getLimit(limit),PlanUtility.getPage(page));
    }
}
