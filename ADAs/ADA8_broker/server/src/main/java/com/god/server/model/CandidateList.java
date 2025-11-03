package com.god.server.model;

import java.util.ArrayList;

public class CandidateList {
    private ArrayList<Candidate> candidateList;
    private CandidateDao candidateDao;

    public CandidateList() {
        candidateDao = new CandidateDao();
        candidateList = candidateDao.retrieveCandidates();
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
        candidateDao.updateFile(candidateList);
    }
}
