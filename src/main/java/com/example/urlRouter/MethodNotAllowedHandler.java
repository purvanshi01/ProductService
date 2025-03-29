package com.example.urlRouter;

import java.util.Map;

public class MethodNotAllowedHandler implements Handler {
    @Override
    public void executeHandler(Map<String, String> params) {
        System.out.println("Method not allowed for this url");
    }
}
