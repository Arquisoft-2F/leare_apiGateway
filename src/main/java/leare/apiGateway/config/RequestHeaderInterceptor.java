package leare.apiGateway.config;

import java.util.Collections;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;

import leare.apiGateway.errors.AuthError;
import reactor.core.publisher.Mono;

import java.util.List;

public class RequestHeaderInterceptor implements WebGraphQlInterceptor {
    private final List<String> excludedOperations;

    public RequestHeaderInterceptor(List<String> excludedOperations) {
        this.excludedOperations = excludedOperations;
    }

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        if (shouldIntercept(request)) {
            String value = request.getHeaders().getFirst("Authorization");
            System.out.println(">>>>>>>>>>>>>>---------------------" + value);
            
            if (value == null) {
                return Mono.error(new AuthError("Authorization header is missing"));  //TODO: this is not handled   
            }
            
            request.configureExecutionInput((executionInput, builder) ->
                    builder.graphQLContext(Collections.singletonMap("Authorization", value)).build());
        }
        return chain.next(request);
    }

    private boolean shouldIntercept(WebGraphQlRequest request) {
        // Verifica si el nombre de la operación está en la lista de operaciones excluidas
        System.out.println(">>>>>>>>>>>>>>" + request.getOperationName());
        return !excludedOperations.contains(request.getOperationName());
    }
}