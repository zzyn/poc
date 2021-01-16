package ${groupId}.core.filters;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ${groupId}.core.CoreConstants.DEFAULT_API_ROUTE;

@Component
@Order(-2)
public class CorsMvcFilter extends OncePerRequestFilter {

    private static final String ALLOWED_HEADERS = "Content-Type, Accept, Authorization, If-Match, If-Modified-Since, If-None-Match, If-Unmodified-Since, Accept-Encoding, Accept-Language, Origin, X-EF-TOKEN";
    private static final String ALLOWED_METHODS = "GET, PUT, POST, DELETE, PATCH, OPTIONS";
    private static final String ALLOWED_ORIGIN = "*";
    private static final String MAX_AGE = "3600";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();

        if (path.startsWith(DEFAULT_API_ROUTE)) {

            if (CorsUtils.isCorsRequest(request)) {

                response.setHeader("Access-Control-Allow-Origin", ALLOWED_ORIGIN);

                if (CorsUtils.isPreFlightRequest(request)) {

                    response.setHeader("Access-Control-Allow-Headers", ALLOWED_HEADERS);
                    response.setHeader("Access-Control-Allow-Methods", ALLOWED_METHODS);
                    response.setHeader("Access-Control-Max-Age", MAX_AGE);

                    response.setStatus(HttpStatus.OK.value());

                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
