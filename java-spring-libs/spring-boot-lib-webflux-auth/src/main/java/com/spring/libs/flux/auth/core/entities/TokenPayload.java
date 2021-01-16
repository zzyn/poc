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
@SuperBuilder
@NoArgsConstructor
public abstract class TokenPayload {
    @JsonProperty(Constants.TokenPayloadFields.JWT_ID)
    private UUID jwtId;
    @JsonProperty(Constants.TokenPayloadFields.OWNER)
    private String owner;
    /**
     * seconds since epoch
     */
    @JsonProperty(Constants.TokenPayloadFields.ISSUED_AT)
    private long issueAt;
    /**
     * seconds since epoch
     */
    @JsonProperty(Constants.TokenPayloadFields.EXPIRE_AT)
    private long expireAt;

    /**
     * Tokens are generated in group together, such as <br/>
     * <ul>
     *     <li>id-token</li>
     *     <li>access-token</li>
     *     <li>refresh-token</li>
     * </ul>
     * <br/>
     * We need this id to correlate the group of tokens.
     */
    @JsonProperty(Constants.TokenPayloadFields.CORRELATION_ID)
    private UUID correlationId;
}
