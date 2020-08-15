package com.webflux.cassandra.demo.api.v1;

import com.webflux.cassandra.demo.core.mapper.PlanMapperUtility;
import com.webflux.cassandra.demo.core.utility.PlanUtility;
import com.webflux.cassandra.demo.domain.dto.PlanDto;
import com.webflux.cassandra.demo.domain.model.PlanCollectionRequestModel;
import com.webflux.cassandra.demo.domain.model.PlanQueryModel;
import com.webflux.cassandra.demo.domain.model.PlanRequestModel;
import com.webflux.cassandra.demo.domain.model.PlanUpdateRequestModel;
import com.webflux.cassandra.demo.service.PlanService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Api
@RestController
@RequestMapping("/api/v1/plan")
public class PlanController {

    private final PlanService planService;

    @Autowired
    public PlanController(PlanService planService){
        this.planService=planService;
    }

    @GetMapping(path = "/{productid}/{bucketid}/{planbusinesskey}",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<PlanDto> getPlans(@PathVariable int productid,
                                  @PathVariable  String planbusinesskey,
                                  @PathVariable  int bucketid,
                                  String studentkey,
                                  String systemkey,
                                  Integer limit,
                                  Integer page)  {
        return planService.getPlans(new PlanQueryModel(productid, planbusinesskey, bucketid, studentkey, PlanUtility.toUuid(systemkey)), limit, page);

    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Mono<PlanDto> insert(@Validated @RequestBody PlanRequestModel planRequest){
        return planService.create(PlanMapperUtility.PLAN_ENTITY_MAPPER.planRequestToPlanEntity(planRequest), PlanUtility.INSERT_QUROUM);
    }

    @PostMapping(path = "/batch", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Mono<List<PlanDto>> insertBatch(@Validated @RequestBody PlanCollectionRequestModel planRequests) {
        Mono<List<PlanDto>> result = planService.createBatch(PlanMapperUtility.PLAN_ENTITY_MAPPER.planRequestToPlanEntity(planRequests.getRequests()), PlanUtility.INSERT_QUROUM);
        if (Objects.isNull(result)) {
            throw new RuntimeException("error msg"); //TODO: error description
        }
        return result;
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Boolean> update(@Validated @RequestBody PlanUpdateRequestModel planRequest) {
        return planService.update(PlanMapperUtility.PLAN_ENTITY_MAPPER.planUpdateRequestToPlanEntity(planRequest));
    }

    @DeleteMapping(
            path = "/{productid}/{bucketid}/{planbusinesskey}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Mono<Boolean> delete(@PathVariable int productid, @PathVariable String planbusinesskey, @PathVariable int bucketid, String studentkey, String systemkey){
        return planService.detete(new PlanQueryModel(productid,planbusinesskey,bucketid,studentkey,PlanUtility.toUuid(systemkey)));
    }
}
