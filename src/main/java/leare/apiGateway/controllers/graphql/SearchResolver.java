package leare.apiGateway.controllers.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import leare.apiGateway.controllers.consumers.CourseConsumer;
import leare.apiGateway.controllers.consumers.SearchConsumer;
import leare.apiGateway.models.CoursesModels.Category;
import leare.apiGateway.models.SearchModels.ResponsePost;

@Controller
public class SearchResolver {

    private final SearchConsumer searchConsumer;

    @Autowired
    public SearchResolver() {
        this.searchConsumer = new SearchConsumer();
    }

    @QueryMapping
    public ResponsePost[] getPosts(@Argument String q) {
        System.out.println(q);
        return searchConsumer.getbyIndex(q);
    }
}
