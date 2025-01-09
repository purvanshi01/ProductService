package com.example.loggerlib.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Namespace {
    private String appId;

    public Namespace(String appId) {
        this.appId = appId;
    }
}
