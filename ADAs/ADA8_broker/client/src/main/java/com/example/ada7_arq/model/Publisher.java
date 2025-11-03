package com.example.ada7_arq.model;

import java.util.ArrayList;

import com.example.ada7_arq.view.Subscriber;

public class Publisher {
    ArrayList<Subscriber> subscribers;

    public Publisher() {
        subscribers = new ArrayList<Subscriber>();
    }

    public void addSubscriber(Subscriber sub) {
        subscribers.add(sub);
    }

    public void update(ArrayList<Candidate> candidateList) {
        for (Subscriber sub : subscribers) {
            sub.update(candidateList);
        }
    }
}
