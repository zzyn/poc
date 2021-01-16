package ${groupId}.core.entity;

import java.io.Serializable;

public class JwtContext implements Serializable {

    private IdentityToken identityToken;
    private AccessToken accessToken;

    public IdentityToken getIdentityToken() {
        return identityToken;
    }

    public JwtContext setIdentityToken(IdentityToken identityToken) {
        this.identityToken = identityToken;
        return this;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public JwtContext setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
        return this;
    }
}
