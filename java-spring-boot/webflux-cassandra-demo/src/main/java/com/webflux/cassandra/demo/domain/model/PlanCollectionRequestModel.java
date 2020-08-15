package com.webflux.cassandra.demo.domain.model;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

/**
 * PlanCollectionRequestModel class
 *
 * @author ef edtech
 */
@Data
public class PlanCollectionRequestModel {
    @Valid
    private
    List<PlanRequestModel> requests;
}
