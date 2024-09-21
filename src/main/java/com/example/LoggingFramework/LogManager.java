package com.example.LoggingFramework;

public class LogManager {
    protected static AbstractLogger buildChainOfLogger() {
        AbstractLogger infoLogger = new InfoLogger(1);
        AbstractLogger errorLogger = new ErrorLogger(2);
        AbstractLogger debugLogger = new DebugLogger(3);

        infoLogger.setNextLoggingLevel(errorLogger);
        errorLogger.setNextLoggingLevel(debugLogger);

        return infoLogger;
    }

    protected static LogSubject buildSubject() {
        LogSubject logSubject = new LogSubject();
        ConsoleLoggerObserver consoleLoggerObserver = new ConsoleLoggerObserver();
        FileLoggerObserver fileLoggerObserver = new FileLoggerObserver();
        logSubject.addObserver(1, consoleLoggerObserver);
        logSubject.addObserver(2, consoleLoggerObserver);
        logSubject.addObserver(1, fileLoggerObserver);
        return logSubject;
    }
}
