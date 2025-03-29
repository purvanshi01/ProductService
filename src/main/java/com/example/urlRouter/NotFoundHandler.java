package com.example.urlRouter;

import java.util.Map;

public class NotFoundHandler implements Handler {
    @Override
    public void executeHandler(Map<String, String> params) {
        System.out.println("Cannot find a handler for this url");
    }
}
