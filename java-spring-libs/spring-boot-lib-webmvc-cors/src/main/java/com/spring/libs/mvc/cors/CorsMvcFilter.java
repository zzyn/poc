package com.spring.libs.mvc.cors;

import org.springframework.http.HttpStatus;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Define the CorsMvcFilter
 */
public class CorsMvcFilter extends OncePerRequestFilter {

    private CorsConfig corsConfig;

    public CorsMvcFilter(CorsConfig corsConfig) {
        this.corsConfig = corsConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String path = httpServletRequest.getServletPath();

        //check the path start with
        if (checkPathWithApiRoute(path, corsConfig.getDefaultApiRoute())) {

            if (CorsUtils.isCorsRequest(httpServletRequest)) {

                httpServletResponse.setHeader("Access-Control-Allow-Origin", corsConfig.getAllowedOrigin());

                if (CorsUtils.isPreFlightRequest(httpServletRequest)) {

                    httpServletResponse.setHeader("Access-Control-Allow-Headers", corsConfig.getAllowedHeaders());
                    httpServletResponse.setHeader("Access-Control-Allow-Methods", corsConfig.getAllowedMethods());
                    httpServletResponse.setHeader("Access-Control-Max-Age", corsConfig.getMaxAge());

                    httpServletResponse.setStatus(HttpStatus.OK.value());

                    //if this is OPTIONS method, do not forward
                    return;
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * check the path start with the apiRoute
     *
     * @param path
     * @param apiRoute
     * @return
     */
    private boolean checkPathWithApiRoute(String path, String[] apiRoute) {
        for (String ar : apiRoute) {
            if (path.startsWith(ar)) {
                return true;
            }
        }
        return false;
    }
}
