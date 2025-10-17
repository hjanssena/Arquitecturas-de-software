package com.example.ada7_arq.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class ExecutionLog {

    private static ExecutionLog executionLog;
    private String filePath;

    private ExecutionLog() {
        filePath = "ExecutionLog.txt";
    }

    public static ExecutionLog getInstance() {
        if (executionLog == null) {
            executionLog = new ExecutionLog();
        }
        return executionLog;
    }

    public void log(String className, String message) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.APPEND,
                StandardOpenOption.CREATE)) {
            writer.write(LocalDateTime.now() + "-/-" + className + "-/-" + message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}
