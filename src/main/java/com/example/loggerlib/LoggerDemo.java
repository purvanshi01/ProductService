package com.example.loggerlib;

import com.example.loggerlib.config.LoggerConfiguration;
import com.example.loggerlib.exception.InvalidInputException;
import com.example.loggerlib.impl.LoggerImpl;
import com.example.loggerlib.model.*;
import com.example.loggerlib.sink.SinkType;

public class LoggerDemo {
    public static void main(String[] args) throws InvalidInputException, InterruptedException {
        LoggerConfiguration loggerConfiguration= new LoggerConfiguration(SinkType.STDOUT, LoggerType.ASYNC, LogLevel.DEBUG);
        Logger logger =  new LoggerImpl(loggerConfiguration);
        Message message = new Message();
        Message message1 = new Message();
        Message message2 = new Message();
        Message message3 = new Message();
        Message message4 = new Message();


        message.setContent("I am Debug log");
        message.setNamespace(new Namespace(LoggerDemo.class.getName()));


        message1.setContent("I am Info log");
        message1.setNamespace(new Namespace(LoggerDemo.class.getName()));
        message2.setContent("I am Fatal log");
        message2.setNamespace(new Namespace(LoggerDemo.class.getName()));
        message3.setContent("I am Warn log");
        message3.setNamespace(new Namespace(LoggerDemo.class.getName()));
        message4.setContent("I am Error log");
        message4.setNamespace(new Namespace(LoggerDemo.class.getName()));

        logger.info(message1);
        logger.fatal(message2);
        logger.debug(message);
        logger.warn(message3);
    }
}
