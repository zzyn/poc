package com.webflux.cassandra.demo.api.v1;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.SimpleStatement;
import com.webflux.cassandra.demo.core.bind.Consistency;
import com.webflux.cassandra.demo.core.bind.Jwt;
import com.webflux.cassandra.demo.core.cache.ReactiveRedisService;
import com.webflux.cassandra.demo.core.entity.ConsistencyContext;
import com.webflux.cassandra.demo.core.entity.JwtContext;
import com.webflux.cassandra.demo.domain.entity.Plan;
import com.webflux.cassandra.demo.core.CoreConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.data.cassandra.core.UpdateOptions;
import org.springframework.data.cassandra.core.cql.QueryOptions;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;

@Api
@RestController
@RequestMapping("/api/v1/plan")
public class PlanController {

    private final ReactiveCassandraTemplate reactiveCassandraTemplate;
    private final ReactiveRedisService reactiveRedisService;

    @Autowired
    public PlanController(ReactiveCassandraTemplate reactiveCassandraTemplate, ReactiveRedisService reactiveRedisService){
        this.reactiveCassandraTemplate = reactiveCassandraTemplate;
        this.reactiveRedisService = reactiveRedisService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/single",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Mono<Plan> single() throws Exception {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("select * from plan where id = %s ;", ""));
        String cql = sb.toString();
        cql = "select * from plan where id = d7a8fce0-919d-11e9-a991-ad3070da029e ";

        Mono<Plan> p = reactiveCassandraTemplate.selectOne(cql, Plan.class);

        return p;
    }


    @RequestMapping(
            method = RequestMethod.GET,
            path = "/query",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ApiImplicitParams(value={
            @ApiImplicitParam(name = CoreConstants.CONSISTENT_LEVEL_HEADER, value = CoreConstants.CONSISTENT_LEVEL_DESC, required = false, paramType = "header", defaultValue = "QUORUM")
    })
    public Mono<Plan> query(@ApiIgnore @Jwt JwtContext jwtContext, @ApiIgnore @Consistency ConsistencyContext consistencyContext) throws Exception {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("select * from plan where id = %s ;", ""));
        String cql = sb.toString();
        cql = "select * from plan where id = d7a8fce0-919d-11e9-a991-ad3070da029e ";

        //DefaultPreparedStatement
        //BatchStatement
        //BoundStatement
        //SimpleStatement
        //PreparedStatement
        //RegularStatement
        //reactiveCassandraTemplate.selectOne()

        SimpleStatement simpleStatement = new SimpleStatement(cql);
        simpleStatement.setConsistencyLevel(consistencyContext.getConsistencyLevel());

        Mono<Plan> p = reactiveCassandraTemplate.selectOne(simpleStatement, Plan.class);

        return p;
    }


    @RequestMapping(
            method = RequestMethod.GET,
            path = "/insert",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Mono<Plan> insert() throws Exception {

        Plan p = null;

        //insert
        InsertOptions options = InsertOptions
                .builder()
                .consistencyLevel(ConsistencyLevel.ONE)
                .build();

        return reactiveCassandraTemplate.insert(p, options).map(x-> { return x.getEntity() ;});
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/update",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Mono<Plan> update() throws Exception {

        Plan p = null;

        //update
        UpdateOptions options = UpdateOptions
                .builder()
                .consistencyLevel(ConsistencyLevel.ONE)
                .build();

        return reactiveCassandraTemplate.update(p, options).map(x-> { return x.getEntity() ;});
    }


    @RequestMapping(
            method = RequestMethod.GET,
            path = "/delete",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Mono<Boolean> delete() throws Exception {

        Plan p = null;

        //delete
        QueryOptions options = QueryOptions
                .builder()
                .consistencyLevel(ConsistencyLevel.ONE)
                .build();

        return reactiveCassandraTemplate.delete(p, options).map(x-> { return x.wasApplied() ;});

    }

}