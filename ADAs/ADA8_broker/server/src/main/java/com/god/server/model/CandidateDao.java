package com.god.server.model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CandidateDao {
    String filePath = "candidates.txt";

    public void updateFile(ArrayList<Candidate> candidateList) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            for (Candidate candidate : candidateList) {
                writer.write(candidate.getName() + ":" + candidate.getVotes());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public ArrayList<Candidate> retrieveCandidates() {
        ArrayList<Candidate> candidateList = new ArrayList<Candidate>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                candidateList.add(new Candidate(parts[0], Integer.parseInt(parts[1])));
            }
            return candidateList;
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return new ArrayList<Candidate>();
        }
    }
}
