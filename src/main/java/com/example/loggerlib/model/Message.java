package com.example.loggerlib.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Message {
    private String content;
    private LogLevel logLevel;
    private Namespace  namespace;

    public Message(String content, LogLevel logLevel, Namespace namespace) {
        this.content = content;
        this.logLevel = logLevel;
        this.namespace = namespace;
    }
}
