package com.example.urlRouter;

import java.util.Map;

public interface Handler {
    void executeHandler(Map<String, String> params);
}
