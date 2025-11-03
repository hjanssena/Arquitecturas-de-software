package com.example.ada7_arq.model;

import java.util.ArrayList;

import com.example.ada7_arq.network.BrokerConnection;
import com.example.ada7_arq.view.Subscriber;

public class CandidateList {
    private ArrayList<Candidate> candidateList;
    private Publisher publisher;
    private BrokerConnection connection;

    public CandidateList(String ip, int port) {
        publisher = new Publisher();
        connection = new BrokerConnection(ip, port);
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
        candidateList = connection.sendContarRequest();
        return candidateList;
    }

    public void voteForCandidateByName(String name) {
        connection.sendVotarRequest(name, 1);
        connection.sendRegistrarRequest("Se ha registrado un voto para " + name);
        triggerUpdate();
    }

    public void subscribeToCandidateList(Subscriber subscriber) {
        publisher.addSubscriber(subscriber);
    }

    public void triggerUpdate() {
        candidateList = connection.sendContarRequest();
        publisher.update(candidateList);
    }

    public ArrayList<String> getServices(String service) {
        ArrayList<String> services = connection.sendListarServiciosRequest(service);
        return services;
    }

    public ArrayList<String> getEvents() {
        ArrayList<String> events = connection.sendListarEventosRequest();
        return events;
    }
}
