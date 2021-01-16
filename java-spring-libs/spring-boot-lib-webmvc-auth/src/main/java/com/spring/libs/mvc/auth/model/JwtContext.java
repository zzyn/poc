package com.spring.libs.mvc.auth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JwtContext implements Serializable {

    private IdentityToken identityToken;

    private AccessToken accessToken;

    private RefreshToken refreshToken;

}
