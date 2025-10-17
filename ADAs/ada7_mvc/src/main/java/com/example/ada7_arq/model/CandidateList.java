package com.example.ada7_arq.model;

import java.util.ArrayList;

import com.example.ada7_arq.view.Subscriber;

public class CandidateList {
    private ArrayList<Candidate> candidateList;
    private CandidateDao candidateDao;
    private Publisher publisher;

    public CandidateList() {
        candidateDao = new CandidateDao();
        candidateList = candidateDao.retrieveCandidates();
        publisher = new Publisher();
    }

    public Candidate getCandidateByName(String name) {
        // Logear ejecucion
        ExecutionLog.getInstance().log(this.getClass().getName(), "Invocación método getCandidateByName");

        for (Candidate candidate : candidateList) {
            if (candidate.getName().equals(name)) {
                return candidate;
            }
        }
        System.err.println("Candidate with name " + name + " not found");
        return null;
    }

    public ArrayList<Candidate> getCandidates() {
        // Logear ejecucion
        ExecutionLog.getInstance().log(this.getClass().getName(), "Invocación método getCandidates");

        return candidateList;
    }

    public void voteForCandidateByName(String name) {
        // Logear ejecucion
        ExecutionLog.getInstance().log(this.getClass().getName(), "Invocación método voteForCandidateByName");

        Candidate candidate = getCandidateByName(name);
        candidate.addVote();
        candidateDao.updateFile(candidateList);
        publisher.update(candidateList);
    }

    public void subscribeToCandidateList(Subscriber subscriber) {
        // Logear ejecucion
        ExecutionLog.getInstance().log(this.getClass().getName(), "Invocación método subscribeToCandidateList");

        publisher.addSubscriber(subscriber);
    }

    public void triggerUpdate() {
        // Logear ejecucion
        ExecutionLog.getInstance().log(this.getClass().getName(), "Invocación método triggerUpdate");

        publisher.update(candidateList);
    }
}
