package com.example.loggerlib.util;


import com.example.loggerlib.model.Message;
import com.example.loggerlib.sink.SinkType;

import java.util.Date;

public class LogFormatter {

    public static String createFormattedLog(Message message, SinkType sinkType){

        return  sinkType +
                " " +
                new Date() +
                " [" +
                message.getLogLevel() +
                "] " +
                message.getNamespace().getAppId() +
                " "+
                message.getContent();
    }

}
