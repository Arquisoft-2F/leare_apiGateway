package leare.apiGateway.models.AuthModels;

public class RouteRequest {
    private String route;
    private String method;


    // Constructor, getters, and setters
    public RouteRequest(String route, String method) {
        this.route = route;
        this.method = method;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
