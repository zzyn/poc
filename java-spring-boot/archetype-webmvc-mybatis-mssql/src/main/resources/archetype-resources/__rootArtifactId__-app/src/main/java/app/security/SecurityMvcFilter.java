package ${groupId}.app.security;

import ${groupId}.core.config.KeyConfig;
import ${groupId}.core.entity.IdentityToken;
import ${groupId}.core.entity.LegacyTokenInfo;
import ${groupId}.core.exception.ApiException;
import ${groupId}.core.exception.ApiErrorUtils;
import ${groupId}.core.security.IdentityTokenUtils;
import ${groupId}.core.security.KeyUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.util.Objects;

import static ${groupId}.core.CoreConstants.DEFAULT_API_ROUTE;
import static ${groupId}.core.CoreConstants.ID_TOKEN_HEADER;

@Component
@Order(-1)
public class SecurityMvcFilter extends OncePerRequestFilter {

    private final KeyConfig keyConfig;
    private final ResourceLoader resourceLoader;

    @Value("${server.error.include-stacktrace}")
    private String includeStackTrace;

    private Boolean isIncludeStackTrace(){
        return "always".equals(includeStackTrace);
    }

    @Autowired
    public SecurityMvcFilter(KeyConfig keyConfig, ResourceLoader resourceLoader) {
        this.keyConfig = keyConfig;
        this.resourceLoader = resourceLoader;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ApiException {

        String path = request.getServletPath();
        String method = request.getMethod();

        if (path.startsWith(DEFAULT_API_ROUTE) && !HttpMethod.OPTIONS.matches(method)) {

            String jwt = request.getHeader(ID_TOKEN_HEADER);

            if (Objects.isNull(jwt)) {
                ApiException ex = new ApiException(HttpStatus.BAD_REQUEST, String.format("header : %s is requierd", ID_TOKEN_HEADER));
                ApiErrorUtils.setErrorResponse(request, response, ex, isIncludeStackTrace());
                return;
            }

            if (jwt.isEmpty()) {
                ApiException ex = new ApiException(HttpStatus.BAD_REQUEST, String.format("header : %s is empty", ID_TOKEN_HEADER));
                ApiErrorUtils.setErrorResponse(request, response, ex, isIncludeStackTrace());
                return;
            }

            PublicKey publicKey;
            try {
                InputStream inputStream = resourceLoader.getClassLoader().getResourceAsStream(keyConfig.getPublicKey());
                publicKey = KeyUtils.getPublicKey(inputStream);
            } catch (Exception e) {
                ApiException ex = new ApiException(HttpStatus.UNAUTHORIZED, "invalid ssl key");
                ApiErrorUtils.setErrorResponse(request, response, ex, isIncludeStackTrace());
                return;
            }

            Boolean isValidToken;
            try {
                isValidToken = IdentityTokenUtils.verifyToken(jwt, publicKey);
            } catch (Exception e) {
                ApiException ex = new ApiException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
                ApiErrorUtils.setErrorResponse(request, response, ex, isIncludeStackTrace());
                return;
            }

            if (!isValidToken) {
                ApiException ex = new ApiException(HttpStatus.UNAUTHORIZED, "invalid token");
                ApiErrorUtils.setErrorResponse(request, response, ex, isIncludeStackTrace());
                return;
            }

        }

        filterChain.doFilter(request, response);
    }
}
