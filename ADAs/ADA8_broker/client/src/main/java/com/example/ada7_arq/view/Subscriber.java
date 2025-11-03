package com.example.ada7_arq.view;

import java.util.ArrayList;

import com.example.ada7_arq.controller.CandidateController;
import com.example.ada7_arq.model.Candidate;

public interface Subscriber {
    public void update(ArrayList<Candidate> candidateList);

    public void subscribe(CandidateController controller);
}
