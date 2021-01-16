package ${groupId}.core.exception;

import ${groupId}.core.exception.GlobalErrorAttributes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Order(-2)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public GlobalErrorWebExceptionHandler(GlobalErrorAttributes globalErrorAttributes
            , ResourceProperties resourceProperties
            , ApplicationContext applicationContext
            , ServerCodecConfigurer serverCodecConfigurer) {
        super(globalErrorAttributes, resourceProperties, applicationContext);
        super.setMessageWriters(serverCodecConfigurer.getWriters());
        super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(final ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderError);
    }

    private Mono<ServerResponse> renderError(final ServerRequest request){

        final Map<String, Object> errorPropertiesMap = getErrorAttributes(request, !isProduct(activeProfile));

        Integer status = (Integer)errorPropertiesMap.get("status");

        return ServerResponse.status(status)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(errorPropertiesMap));
    }

    private boolean isProduct(String profileName){
        boolean result = false;

        switch (profileName){
            case "prdcn":
            case "prdsg":
                result = true;
                break;
            default:
                break;
        }
        return result;
    }
}
