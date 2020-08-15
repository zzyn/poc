package com.webflux.cassandra.demo.domain.model;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static com.webflux.cassandra.demo.domain.Constants.SYSTEM_KEY_NULL_ERROR_CODE;


/**
 * PlanUpdateRequestModel class
 *
 * @author ef edtech
 */
@Data
public class PlanUpdateRequestModel extends PlanRequestModel {

    @NotNull( message = SYSTEM_KEY_NULL_ERROR_CODE)
    private UUID systemKey;
}
