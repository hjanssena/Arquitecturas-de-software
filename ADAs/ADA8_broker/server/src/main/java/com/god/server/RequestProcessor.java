package com.god.server;

import java.util.ArrayList;
import java.util.List;

import com.god.server.model.Candidate;
import com.god.server.model.CandidateList;
import com.god.server.model.ExecutionLog;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RequestProcessor {

    CandidateList candidateList = new CandidateList();

    public RequestProcessor() {
        this.candidateList = new CandidateList();
    }

    public String processRequest(String request) {
        JsonObject jsonRequest = JsonParser.parseString(request).getAsJsonObject();
        String service = jsonRequest.get("servicio").getAsString();
        JsonObject jsonResponse;
        switch (service) {
            case "contar":
                jsonResponse = contarService();
                break;
            case "votar":
                jsonResponse = votarService(jsonRequest);
                break;
            case "registrar":
                jsonResponse = registrarService(jsonRequest);
                break;
            case "listar":
                jsonResponse = listarService();
                break;
            default:
                return "Invalid service";
        }

        return jsonResponse.toString();
    }

    private JsonObject contarService() {
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("servicio", "contar");
        ArrayList<Candidate> candidates = candidateList.getCandidates();
        jsonResponse.addProperty("respuestas", candidates.size());
        int i = 1;
        for (Candidate candidate : candidates) {
            jsonResponse.addProperty("respuesta" + i, candidate.getName());
            jsonResponse.addProperty("valor" + i, candidate.getVotes());
            i++;
        }

        return jsonResponse;
    }

    private JsonObject votarService(JsonObject jsonRequest) {
        String votedCandidate = jsonRequest.get("variable1").getAsString();
        int votes = jsonRequest.get("valor1").getAsInt();

        for (int i = 0; i < votes; i++) {
            candidateList.voteForCandidateByName(votedCandidate);
        }

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("servicio", "votar");
        jsonResponse.addProperty("respuestas", 1);
        jsonResponse.addProperty("respuesta1", votedCandidate);
        jsonResponse.addProperty("valor1", candidateList.getCandidateByName(votedCandidate).getVotes());

        return jsonResponse;
    }

    private JsonObject registrarService(JsonObject jsonRequest) {
        String evento = jsonRequest.get("valor1").getAsString();
        String fecha = jsonRequest.get("valor2").getAsString();
        ExecutionLog.getInstance().log(fecha + "-/-" + evento);

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("servicio", "registrar");
        jsonResponse.addProperty("respuestas", 1);
        jsonResponse.addProperty("respuesta1", "eventos");
        int entryCount = ExecutionLog.getInstance().getEntryCount();
        jsonResponse.addProperty("valor1", entryCount);

        return jsonResponse;
    }

    private JsonObject listarService() {
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("servicio", "listar");
        jsonResponse.addProperty("respuestas", ExecutionLog.getInstance().getEntryCount());
        List<String> entries = ExecutionLog.getInstance().getLogEntries();
        int i = 1;
        for (String entry : entries) {
            jsonResponse.addProperty("respuesta" + i, "evento");
            jsonResponse.addProperty("valor" + i, entry);
            i++;
        }

        return jsonResponse;
    }
}
