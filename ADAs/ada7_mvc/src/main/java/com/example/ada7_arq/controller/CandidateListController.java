package com.example.ada7_arq.controller;

import java.util.ArrayList;

import com.example.ada7_arq.model.Candidate;
import com.example.ada7_arq.model.CandidateList;
import com.example.ada7_arq.model.ExecutionLog;
import com.example.ada7_arq.view.Subscriber;

public class CandidateListController {

    CandidateList candidateList;

    public CandidateListController(CandidateList candidateList) {
        this.candidateList = candidateList;
    }

    public void submitVote(String candidateName) {
        // Logear ejecucion
        ExecutionLog.getInstance().log(this.getClass().getName(), "Invocación método submitVote");

        candidateList.voteForCandidateByName(candidateName);
    }

    public ArrayList<Candidate> getCandidates() {
        // Logear ejecucion
        ExecutionLog.getInstance().log(this.getClass().getName(), "Invocación método getCandidates");

        return candidateList.getCandidates();
    }

    public void subscribeToCandidateList(Subscriber subscriber) {
        // Logear ejecucion
        ExecutionLog.getInstance().log(this.getClass().getName(), "Invocación método subscribeToCandidateList");

        candidateList.subscribeToCandidateList(subscriber);
    }
}
