package com.example.ada7_arq.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import com.example.ada7_arq.model.Candidate;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BrokerConnection {

    private String ipAddress;
    private int port;

    public BrokerConnection(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public Candidate sendVotarRequest(String candidateName, int voteCount) {
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("servicio", "ejecutar");
        jsonRequest.addProperty("variables", 2);
        jsonRequest.addProperty("variable1", "servicio");
        jsonRequest.addProperty("valor1", "votar");
        jsonRequest.addProperty("variable2", candidateName);
        jsonRequest.addProperty("valor2", voteCount);

        String response = sendRequest(jsonRequest.toString());
        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();

        String name = jsonResponse.get("respuesta2").getAsString();
        int votes = jsonResponse.get("valor2").getAsInt();
        Candidate updatedCandidate = new Candidate(name, votes);

        return updatedCandidate;
    }

    public ArrayList<String> sendListarEventosRequest() {
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("servicio", "ejecutar");
        jsonRequest.addProperty("variables", 1);
        jsonRequest.addProperty("variable1", "servicio");
        jsonRequest.addProperty("valor1", "listar");

        String response = sendRequest(jsonRequest.toString());
        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();

        ArrayList<String> outEventos = new ArrayList<String>();
        int i = 2;
        while (jsonResponse.has("respuesta" + i)) {
            outEventos.add(jsonResponse.get("valor" + i).getAsString());
            i++;
        }

        return outEventos;
    }

    public ArrayList<Candidate> sendContarRequest() {
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("servicio", "ejecutar");
        jsonRequest.addProperty("variables", 1);
        jsonRequest.addProperty("variable1", "servicio");
        jsonRequest.addProperty("valor1", "contar");

        String response = sendRequest(jsonRequest.toString());
        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();

        ArrayList<Candidate> outCandidates = new ArrayList<Candidate>();
        int i = 2;
        while (jsonResponse.has("respuesta" + i)) {
            String name = jsonResponse.get("respuesta" + i).getAsString();
            int votes = jsonResponse.get("valor" + i).getAsInt();
            outCandidates.add(new Candidate(name, votes));
            i++;
        }

        return outCandidates;
    }

    public void sendRegistrarRequest(String event) {
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("servicio", "ejecutar");
        jsonRequest.addProperty("variables", 2);
        jsonRequest.addProperty("variable1", "servicio");
        jsonRequest.addProperty("valor1", "registrar");
        jsonRequest.addProperty("variable2", "evento");
        jsonRequest.addProperty("valor2", event);
        jsonRequest.addProperty("variable3", "fecha");
        jsonRequest.addProperty("valor3",
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));

        String response = sendRequest(jsonRequest.toString());

        if (response.isEmpty()) {
            System.out.println("Respuesta de evento " + event + " no ha sido recibida.");
        }
    }

    public ArrayList<String> sendListarServiciosRequest(String servicio) {
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("servicio", "listar");
        jsonRequest.addProperty("variables", 1);
        jsonRequest.addProperty("variable1", "palabra");
        jsonRequest.addProperty("valor1", servicio);

        String response = sendRequest(jsonRequest.toString());
        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();

        ArrayList<String> outServicios = new ArrayList<String>();
        int i = 1;
        while (jsonResponse.has("respuesta" + i)) {
            String service = jsonResponse.get("respuesta" + i).getAsString();
            String ip = jsonResponse.get("valor" + i).getAsString();
            outServicios.add(service + " - " + ip);
            i++;
        }

        return outServicios;
    }

    public String sendRequest(String request) {
        try {
            Socket socket = new Socket(ipAddress, port);

            OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write(request + "\n");
            writer.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = reader.readLine();

            writer.close();
            reader.close();
            socket.close();
            System.out.println(response);
            return response;

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
