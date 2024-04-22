package leare.apiGateway.controllers.graphql;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import leare.apiGateway.controllers.consumers.AuthConsumer;
import leare.apiGateway.controllers.consumers.CourseConsumer;
import leare.apiGateway.controllers.consumers.DocumentConsumer;
import leare.apiGateway.controllers.consumers.SearchConsumer;
import leare.apiGateway.errors.AuthError;
import leare.apiGateway.models.AuthModels.DecryptedToken;
import leare.apiGateway.models.CoursesModels.Category;
import leare.apiGateway.models.SearchModels.Post;
import leare.apiGateway.models.SearchModels.ResponsePost;

@Controller
public class SearchResolver {

    private final SearchConsumer searchConsumer;
    private final AuthConsumer authConsumer;
    private final DocumentConsumer documentConsumer;

    @Autowired
    public SearchResolver(SearchConsumer searchConsumer, AuthConsumer authConsumer, DocumentConsumer documentConsumer) {
        this.searchConsumer = searchConsumer;
        this.authConsumer = authConsumer;
        this.documentConsumer = documentConsumer;
    }

    @QueryMapping
    public ResponsePost[] getPosts(@Argument String q, @ContextValue("Authorization") String AuthorizationHeader) throws Exception {

        Boolean Auth = authConsumer.CheckRoute("/posts", "get", AuthorizationHeader);

        if (!Auth) {
            throw new AuthError("Auth Problem : Acces denied to this route");
        }
        
        ResponsePost[] res = searchConsumer.getbyIndex(q);

        String[] pictureIds = new String[res.length];

        for (int i = 0; i < res.length; i++) {
            pictureIds[i] = res[i].getPost().getPicture().get();
        }
        
        leare.apiGateway.models.DocumentModels.batch.GetBatchResponse allPictures = documentConsumer.batchGetDocuments(pictureIds);
        for (ResponsePost user : res) {
            try {
                Optional<String> link = Optional.of(allPictures.getValue().get(user.getPost().getPicture().get()).getFilePath());
                user.getPost().setPicture(link);
            } catch (Exception e) {
                user.getPost().setPicture(Optional.of("notFound"));
            }
        }

        return res;
    }
}
