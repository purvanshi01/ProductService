package com.example.LoggingFramework;

import static com.example.LoggingFramework.LogManager.buildChainOfLogger;
import static com.example.LoggingFramework.LogManager.buildSubject;

public class Logger {
    public static Logger logger;
    public static AbstractLogger chainOfLogger;
    public static LogSubject logSubject;
    public static Logger getInstance() {
        if (logger == null) {
            synchronized (Logger.class) {
                if (logger == null) {
                    logger = new Logger();
                    chainOfLogger = buildChainOfLogger();
                    logSubject = buildSubject();
                }
            }
        }
        return logger;
    }

    void createLog(int level, String message, LogSubject logSubject) {
        chainOfLogger.logMessage(level, message, logSubject);
    }

    public void info(String message) {
        createLog(1, message, logSubject);
    }

    public void error(String message) {
        createLog(2, message, logSubject);
    }

    public void debug(String message) {
        createLog(3, message, logSubject);
    }
}
