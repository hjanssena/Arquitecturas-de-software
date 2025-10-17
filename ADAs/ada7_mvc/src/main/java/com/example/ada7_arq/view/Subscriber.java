package com.example.ada7_arq.view;

import java.util.ArrayList;

import com.example.ada7_arq.controller.CandidateListController;
import com.example.ada7_arq.model.Candidate;

public interface Subscriber {
    public void update(ArrayList<Candidate> candidateList);

    public void subscribe(CandidateListController controller);
}
