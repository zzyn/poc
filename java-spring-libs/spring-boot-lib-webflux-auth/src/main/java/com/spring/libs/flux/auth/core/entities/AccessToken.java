package com.spring.libs.flux.auth.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.libs.flux.auth.core.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class AccessToken extends TokenPayload {
    @JsonProperty(Constants.TokenPayloadFields.ACL_REF_ID)
    private UUID aclRefId;
}
