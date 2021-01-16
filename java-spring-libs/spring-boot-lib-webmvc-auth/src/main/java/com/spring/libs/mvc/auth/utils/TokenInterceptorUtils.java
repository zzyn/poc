package com.spring.libs.mvc.auth.utils;

import com.spring.libs.jwt.utils.TokenUtils;
import com.spring.libs.mvc.auth.model.AccessToken;
import com.spring.libs.mvc.exception.ApiErrorCode;
import com.spring.libs.mvc.exception.ApiException;
import com.spring.libs.mvc.auth.constants.AuthConstants;
import com.spring.libs.mvc.auth.model.enums.AccessTokenType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;
import java.util.Objects;

@Slf4j
public class TokenInterceptorUtils {

    public static boolean verifyToken(HttpServletRequest request, String header, PublicKey publicKey) {

        String jwtToken = request.getHeader(header);

        if (Objects.isNull(jwtToken)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ApiErrorCode.APP, String.format("header : %s is required", header));
        }

        if (jwtToken.isEmpty()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ApiErrorCode.APP, String.format("header : %s is empty", header));
        }

        try {
            TokenUtils.verifyToken(jwtToken, publicKey);
        } catch (Exception e) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, ApiErrorCode.APP, e);
        }

        return true;
    }

    public static boolean isAccessTokenServiceToken(HttpServletRequest request, PublicKey publicKey) {
        try {
            return StringUtils.compareIgnoreCase(TokenUtils.decodeToken(request.getHeader(AuthConstants.ACCESS_TOKEN_HEADER), publicKey, AccessToken.class).getType()
                    , AccessTokenType.service.name()) == 0;
        } catch (Exception e) {
            return false;
        }
    }
}
