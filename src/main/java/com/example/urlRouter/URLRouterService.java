package com.example.urlRouter;

import java.util.HashMap;
import java.util.Map;

public class URLRouterService {
    private final RouteNode root = new RouteNode();
    private final NotFoundHandler notFoundHandler = new NotFoundHandler();
    private final MethodNotAllowedHandler methodNotAllowedHandler = new MethodNotAllowedHandler();


    public void addRoute (HTTPMethod method, String url, Handler handler) {
        RouteNode currentNode = root;
        String[] pathElements = url.split("/");
        for (String element : pathElements) {
            if (element.isEmpty()) continue;
            if (element.startsWith(":")) {
                if (currentNode.dynamicRoute == null) {
                    currentNode.dynamicRoute = new RouteNode();
                    currentNode.dynamicRouteKey = element.substring(1);
                }
                currentNode = currentNode.dynamicRoute;
            } else if (element.startsWith("*")) {
                if (currentNode.wildcardRouteKey == null) {
                    currentNode.wildcardRouteKey = new RouteNode();
                }
                currentNode = currentNode.wildcardRouteKey;
                break;
            } else {
                currentNode.childrenRouteNodeMap.putIfAbsent(element, new RouteNode());
                currentNode = currentNode.childrenRouteNodeMap.get(element);
            }
        }

        currentNode.setHandler(method, handler);
    }

    public void callRoute(HTTPMethod method, String url) {
        Map<String, String> params = new HashMap<>();
        RouteNode currentNode = root;
        String[] pathElements = url.split("/");
        for (String element : pathElements) {
            if (element.isEmpty()) continue;
            if (currentNode.childrenRouteNodeMap.containsKey(element)) {
                currentNode = currentNode.childrenRouteNodeMap.get(element);
            } else if (currentNode.dynamicRoute != null) {
                params.put(currentNode.dynamicRouteKey, element);
                currentNode = currentNode.dynamicRoute;
            } else if (currentNode.wildcardRouteKey != null) {
                params.put("filePath", url.substring(url.indexOf(element)));
                currentNode = currentNode.wildcardRouteKey;
                break;
            } else {
                notFoundHandler.executeHandler(params);
                return;
            }
        }

        if (currentNode.hasHandler(method)) {
            currentNode.getHandler(method).executeHandler(params);
        } else if (currentNode.httpMethodHandlerMap.isEmpty()) {
            notFoundHandler.executeHandler(params);
        } else {
            methodNotAllowedHandler.executeHandler(params);
        }

    }
}
