package com.example.ada7_arq.controller;

import java.util.ArrayList;

import com.example.ada7_arq.model.Candidate;
import com.example.ada7_arq.model.CandidateList;
import com.example.ada7_arq.view.Subscriber;

public class CandidateController {
    private CandidateList candidateList;

    public CandidateController(CandidateList candidateList) {
        this.candidateList = candidateList;
    }

    public void submitVote(String candidateName) {
        candidateList.voteForCandidateByName(candidateName);
    }

    public ArrayList<Candidate> getCandidates() {
        return candidateList.getCandidates();
    }

    public void subscribeToCandidateList(Subscriber subscriber) {
        candidateList.subscribeToCandidateList(subscriber);
    }

    public ArrayList<String> getServices(String service) {
        return candidateList.getServices(service);
    }

    public ArrayList<String> getEvents() {
        return candidateList.getEvents();
    }
}
