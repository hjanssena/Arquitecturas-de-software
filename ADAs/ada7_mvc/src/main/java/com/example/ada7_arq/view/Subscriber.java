package com.example.ada7_arq.view;

import java.util.ArrayList;

import com.example.ada7_arq.model.Candidate;
import com.example.ada7_arq.model.Publisher;

public interface Subscriber {
    public void update(ArrayList<Candidate> candidateList);

    public void subscribe(Publisher publisher);
}
