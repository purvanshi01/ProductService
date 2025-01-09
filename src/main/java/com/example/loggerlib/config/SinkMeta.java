package com.example.loggerlib.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SinkMeta {
    private String filePath;
    private Object dbConfiguration;
}
