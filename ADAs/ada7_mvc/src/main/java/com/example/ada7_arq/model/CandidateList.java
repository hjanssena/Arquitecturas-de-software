package com.example.ada7_arq.model;

import java.util.ArrayList;

public class CandidateList {
    private ArrayList<Candidate> candidateList;
    private CandidateDao candidateDao;
    private Publisher publisher;

    public CandidateList(Publisher publisher) {
        candidateDao = new CandidateDao();
        candidateList = candidateDao.retrieveCandidates();
        this.publisher = publisher;
    }

    public Candidate getCandidateByName(String name) {
        for (Candidate candidate : candidateList) {
            if (candidate.getName().equals(name)) {
                return candidate;
            }
        }
        System.err.println("Candidate with name " + name + " not found");
        return null;
    }

    public ArrayList<Candidate> getCandidates() {
        return candidateList;
    }

    public void voteForCandidateByName(String name) {
        Candidate candidate = getCandidateByName(name);
        candidate.addVote();
        candidateDao.UpdateFile(candidateList);
        publisher.update(candidateList);
    }
}
