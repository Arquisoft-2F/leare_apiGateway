package leare.apiGateway.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.server.WebGraphQlInterceptor;

@Configuration
public class GraphQLInterceptorConfig {

    @Bean
    public WebGraphQlInterceptor requestHeaderInterceptor() {
        List<String> excludedOperations = Arrays.asList( "IntrospectionQuery", "login", "CreateUser", "editPassword");
        return new RequestHeaderInterceptor(excludedOperations);
    }
}
