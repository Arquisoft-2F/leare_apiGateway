package leare.apiGateway.config;

import java.util.Collections;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public class RequestHeaderInterceptor implements WebGraphQlInterceptor {
    private String value;
    private final List<String> excludedOperations;

    public RequestHeaderInterceptor(List<String> excludedOperations) {
        this.excludedOperations = excludedOperations;
    }

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        if (shouldIntercept(request)) {
            try {
                this.value = request.getHeaders().getFirst("Authorization");
            } catch (Exception e) {
                this.value = "";
            }
            System.out.println(value);
            request.configureExecutionInput((executionInput, builder) ->
                    builder.graphQLContext(Collections.singletonMap("Authorization", value)).build());
        }
        return chain.next(request);
    }

    private boolean shouldIntercept(WebGraphQlRequest request) {
        // Verifica si el nombre de la operación está en la lista de operaciones excluidas
        return !excludedOperations.contains(request.getOperationName());
    }
}