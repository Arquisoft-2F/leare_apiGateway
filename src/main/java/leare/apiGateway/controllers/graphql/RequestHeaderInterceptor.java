package leare.apiGateway.controllers.graphql;

import java.util.Collections;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import reactor.core.publisher.Mono;

public class RequestHeaderInterceptor implements WebGraphQlInterceptor { 
    private String value; 

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        try {
            this.value = request.getHeaders().getFirst("Authorization");
            
        } catch (Exception e) {
            this.value = "";
        }
        System.out.println(value);
        request.configureExecutionInput((executionInput, builder) ->
                builder.graphQLContext(Collections.singletonMap("Authorization", value)).build());
        return chain.next(request);
    }
}