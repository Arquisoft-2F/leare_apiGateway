package leare.apiGateway.controllers.graphql;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.server.WebGraphQlInterceptor;

@Configuration
public class GraphQLInterceptorConfig {

    @Bean
    public WebGraphQlInterceptor requestHeaderInterceptor() {
        return new RequestHeaderInterceptor();
    }
}
