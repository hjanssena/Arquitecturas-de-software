package com.example.ada7_arq.controller;

import java.util.ArrayList;

import com.example.ada7_arq.model.Candidate;
import com.example.ada7_arq.model.CandidateList;

public class CandidateListController {

    CandidateList candidateList;

    public CandidateListController(CandidateList candidateList) {
        this.candidateList = candidateList;
    }

    public void SubmitVote(String candidateName) {
        candidateList.voteForCandidateByName(candidateName);
    }

    public ArrayList<Candidate> getCandidates() {
        return candidateList.getCandidates();
    }
}
