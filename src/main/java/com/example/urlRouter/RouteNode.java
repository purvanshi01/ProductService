package com.example.urlRouter;

import org.springframework.util.RouteMatcher;

import java.util.HashMap;
import java.util.Map;

public class RouteNode {
    Map <HTTPMethod, Handler> httpMethodHandlerMap;
    Map <String, RouteNode> childrenRouteNodeMap;
    RouteNode dynamicRoute;
    String dynamicRouteKey;
    RouteNode wildcardRouteKey;

    public RouteNode() {
        httpMethodHandlerMap = new HashMap<>();
        childrenRouteNodeMap = new HashMap<>();
        dynamicRoute = null;
        dynamicRouteKey = "";
        wildcardRouteKey = null;
    }

    public boolean hasHandler(HTTPMethod method) {
        return httpMethodHandlerMap.containsKey(method);
    }

    public Handler getHandler(HTTPMethod method) {
        return httpMethodHandlerMap.get(method);
    }

    public void setHandler(HTTPMethod method, Handler handler) {
        httpMethodHandlerMap.put(method, handler);
    }

}
