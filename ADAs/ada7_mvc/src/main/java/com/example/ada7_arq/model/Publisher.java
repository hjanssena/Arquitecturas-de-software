package com.example.ada7_arq.model;

import java.util.ArrayList;

import com.example.ada7_arq.view.Subscriber;

public class Publisher {
    ArrayList<Subscriber> subscribers;

    public Publisher() {
        subscribers = new ArrayList<Subscriber>();
    }

    public void addSubscriber(Subscriber sub) {
        // Logear ejecucion
        ExecutionLog.getInstance().log(this.getClass().getName(), "Invocación método addSubscriber");

        subscribers.add(sub);
    }

    public void update(ArrayList<Candidate> candidateList) {
        // Logear ejecucion
        ExecutionLog.getInstance().log(this.getClass().getName(), "Invocación método update");

        for (Subscriber sub : subscribers) {
            sub.update(candidateList);
        }
    }
}
