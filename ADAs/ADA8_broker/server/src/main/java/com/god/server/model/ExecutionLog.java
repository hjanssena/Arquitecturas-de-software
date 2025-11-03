package com.god.server.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ExecutionLog {

    private static ExecutionLog executionLog;
    private String filePath;
    private int entryCount = 0;

    private ExecutionLog() {
        filePath = "ExecutionLog.txt";
    }

    public static ExecutionLog getInstance() {
        if (executionLog == null) {
            executionLog = new ExecutionLog();
        }
        return executionLog;
    }

    public void log(String message) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.APPEND,
                StandardOpenOption.CREATE)) {
            writer.write(message);
            writer.newLine();
            entryCount++;
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public int getEntryCount() {
        return entryCount;
    }

    public List<String> getLogEntries() {
        List<String> entries = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                entries.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from the file: " + e.getMessage());
        }
        return entries;
    }
}
