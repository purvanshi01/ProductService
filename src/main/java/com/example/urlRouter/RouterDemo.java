package com.example.urlRouter;

public class RouterDemo {
    public static void main(String[] args) {
        URLRouterService router = new URLRouterService();
        router.addRoute(HTTPMethod.GET, "/home", params -> System.out.println("GET /home handler called"));
        // Define some handler functions
        router.addRoute(HTTPMethod.GET, "/home", params -> System.out.println("GET /home handler called"));
        router.addRoute(HTTPMethod.POST, "/users", params -> System.out.println("POST /users handler called"));
        router.addRoute(HTTPMethod.GET, "/users/:userId", params ->
                System.out.println("GET /users/:userId handler called, userId = " + params.get("userId")));
        router.addRoute(HTTPMethod.GET, "/users/:userId/orders/:orderId", params ->
                System.out.println("GET /users/:userId/orders/:orderId handler called, userId = " + params.get("userId") + ", orderId = " + params.get("orderId")));
        router.addRoute(HTTPMethod.GET, "/static/*", params ->
                System.out.println("GET /static/*filePath handler called, filePath = " + params.get("filePath")));

        // Test routing
        router.callRoute(HTTPMethod.GET, "/home"); // Output: GET /home handler called
        router.callRoute(HTTPMethod.POST, "/users"); // Output: POST /users handler called
        router.callRoute(HTTPMethod.GET, "/users/123"); // Output: GET /users/:userId handler called, userId = 123
        router.callRoute(HTTPMethod.GET, "/users/123/orders/456"); // Output: GET /users/:userId/orders/:orderId handler called, userId = 123, orderId = 456
        router.callRoute(HTTPMethod.GET, "/static/images/logo.png"); // Output: GET /static/*filePath handler called, filePath = images/logo.png
        router.callRoute(HTTPMethod.GET, "/unknown"); // Output: 404 Not Found
        router.callRoute(HTTPMethod.POST, "/users/123"); // Output: 405 Method Not Allowed
    }
}
