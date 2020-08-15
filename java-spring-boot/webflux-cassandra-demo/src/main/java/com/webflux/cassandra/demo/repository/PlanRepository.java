package com.webflux.cassandra.demo.repository;

import com.datastax.driver.core.ConsistencyLevel;

import com.webflux.cassandra.demo.core.mapper.PlanMapperUtility;
import com.webflux.cassandra.demo.core.utility.PlanUtility;
import com.webflux.cassandra.demo.domain.dto.PlanDto;
import com.webflux.cassandra.demo.domain.entity.PlanEntity;
import com.webflux.cassandra.demo.domain.model.PlanQueryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.data.cassandra.core.ReactiveCassandraOperations;
import org.springframework.data.cassandra.core.WriteResult;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * PlanRepository class
 *
 * @author ef edtech
 */
@Repository
public class PlanRepository {

    private final ReactiveCassandraOperations reactiveCassandraTemplate;

    private final Logger logger = LoggerFactory.getLogger(PlanRepository.class);
    public PlanRepository(ReactiveCassandraOperations reactiveCassandraTemplate) {
        this.reactiveCassandraTemplate = reactiveCassandraTemplate;
    }

    public Flux<PlanDto> queryByWhere (PlanQueryModel query, int limit, int page){
        String queryCql = CqlHelper.selectCql(query, limit*page);
        return reactiveCassandraTemplate.getReactiveCqlOperations().query(queryCql, preparedStatement -> {
            preparedStatement.setConsistencyLevel(ConsistencyLevel.QUORUM);
            return preparedStatement.bind();
        },PlanMapper.INSTANCE).skip((long)limit*(page-1)).take(limit);
    }

    public Mono<PlanDto> insert(PlanEntity plan, InsertOptions options) {
       return reactiveCassandraTemplate.insert(plan,options).map(x-> {
           try {
               return PlanMapperUtility.PLAN_DTO_MAPPER.planEntityToPlanDto(x.getEntity());
           } catch (Exception e) {
               logger.error("insert",e);
               return null;
           }
       });
    }

    public Mono<Boolean> update(PlanEntity plan) {

      return  reactiveCassandraTemplate.update(plan, PlanUtility.UPDATE_QUROUM).map(WriteResult::wasApplied);
    }

    public Mono<List<PlanDto>> insertBatch(List<PlanEntity> plans, InsertOptions options) {
       return reactiveCassandraTemplate.batchOps().insert(plans,options).execute().map(x-> {
           try {
               return PlanMapperUtility.PLAN_DTO_MAPPER.planRequestCollectionToPlanDto(plans);
           } catch (Exception e) {
               logger.error("insertBatch",e);
               return null;
           }
       });
    }

    public Mono<Boolean> detete(PlanQueryModel query, final @NotNull ConsistencyLevel consistencyLevel)
    {
        String deleteCql = CqlHelper.deleteCql(query);
        return reactiveCassandraTemplate.getReactiveCqlOperations().execute(deleteCql, preparedStatement -> {
            preparedStatement.setConsistencyLevel(consistencyLevel);
            return preparedStatement.bind();
        });
    }
}

