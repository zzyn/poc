package com.spring.libs.flux.auth.core;

public class Constants {

    private Constants() {}

    public static class HttpConstants {

        private HttpConstants() {}

        /**
         * HTTP header for ID token.
         * We keep the same name as the legacy since we have to maintain backward compatibility.
         */
        public static final String HEADER_X_EF_TOKEN = "X-EF-TOKEN";
        public static final String HEADER_ACCESS_TOKEN = "EF-Access-Token";
    }

    public static class ProfileFields {

        private ProfileFields() {}

        public static final String LAST_NAME = "family_name";
        public static final String FIRST_NAME = "given_name";
        public static final String AVATAR = "picture";
        public static final String GENDER = "gender";
        public static final String COUNTRY = "market_region";
    }

    public static class TokenPayloadFields {

        private TokenPayloadFields() {}

        public static final String JWT_ID = "jti";
        /**
         * Subject the token belongs to.
         */
        public static final String OWNER = "sub";

        public static final String ISSUED_AT = "iat";

        public static final String EXPIRE_AT = "exp";

        public static final String CORRELATION_ID = "correlation_id";

        public static final String ACL_REF_ID = "ref_id";
    }
}
