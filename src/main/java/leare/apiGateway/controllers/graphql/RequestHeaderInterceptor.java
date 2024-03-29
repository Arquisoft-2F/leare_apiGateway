package leare.apiGateway.controllers.graphql;

import java.util.Collections;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import reactor.core.publisher.Mono;

public class RequestHeaderInterceptor implements WebGraphQlInterceptor { 

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {

        String value = request.getHeaders().getFirst("Authorization");
        System.out.println(value);
        request.configureExecutionInput((executionInput, builder) ->
                builder.graphQLContext(Collections.singletonMap("Authorization", value)).build());
        return chain.next(request);
    }
}