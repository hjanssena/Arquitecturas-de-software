package com.example.ada7_arq.model;

public class Candidate {
    private int votes;
    private String name;

    public Candidate(String name, int votes) {
        this.name = name;
        this.votes = votes;
    }

    public int getVotes() {
        ExecutionLog.getInstance().log(this.getClass().getName(), "Invocación método getVotes");
        return votes;
    }

    public String getName() {
        ExecutionLog.getInstance().log(this.getClass().getName(), "Invocación método getName");
        return name;
    }

    public void addVote() {
        votes++;
        ExecutionLog.getInstance().log(this.getClass().getName(), "Invocación método addVote");
    }
}
