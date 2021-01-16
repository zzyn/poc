package com.spring.libs.flux.auth.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.libs.flux.auth.core.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IdentityToken extends TokenPayload{
    @JsonProperty(Constants.ProfileFields.LAST_NAME)
    private String lastName;
    @JsonProperty(Constants.ProfileFields.FIRST_NAME)
    private String firstName;
    @JsonProperty(Constants.ProfileFields.GENDER)
    private String gender;
    @JsonProperty(Constants.ProfileFields.COUNTRY)
    private String country;
    @JsonProperty(Constants.ProfileFields.AVATAR)
    private String avatar;
}
