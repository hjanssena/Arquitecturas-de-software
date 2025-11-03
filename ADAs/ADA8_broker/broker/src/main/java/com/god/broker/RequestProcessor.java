package com.god.broker;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RequestProcessor {

    private List<Service> services;
    RequestSender requestSender;

    public RequestProcessor() {
        requestSender = new RequestSender();
        this.services = new ArrayList<Service>();
    }

    public String processRequest(String request) {
        JsonObject jsonRequest = JsonParser.parseString(request).getAsJsonObject();
        String service = jsonRequest.get("servicio").getAsString();
        JsonObject jsonResponse;
        switch (service) {
            case "registrar":
                jsonResponse = registrarService(jsonRequest);
                break;
            case "listar":
                jsonResponse = listarService(jsonRequest);
                break;
            case "ejecutar":
                jsonResponse = ejecutarService(jsonRequest);
                break;
            default:
                return "Invalid service";
        }

        return jsonResponse.toString();
    }

    private JsonObject registrarService(JsonObject jsonRequest) {
        String serviceIP = jsonRequest.get("valor1").getAsString();
        int servicePort = jsonRequest.get("valor2").getAsInt();
        String serviceName = jsonRequest.get("valor3").getAsString();
        int parameterCount = jsonRequest.get("valor4").getAsInt();

        Service service = new Service(serviceIP, servicePort, serviceName, parameterCount);
        services.add(service);

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("servicio", "registrar");
        jsonResponse.addProperty("respuestas", 1);
        jsonResponse.addProperty("respuesta1", "identificador");
        jsonResponse.addProperty("valor1", services.size());

        return jsonResponse;
    }

    private JsonObject listarService(JsonObject jsonRequest) {
        String requestedService = jsonRequest.has("valor1") ? jsonRequest.get("valor1").getAsString() : "";

        JsonObject jsonResponse = new JsonObject();
        int serviceCount = 0;
        try {
            for (Service service : services) {
                if (requestedService.isEmpty() || service.getServiceName().equals(requestedService)) {
                    serviceCount++;
                    jsonResponse.addProperty("respuesta" + serviceCount, service.getServiceName());
                    jsonResponse.addProperty("valor" + serviceCount, service.getIpAddress() + ":" + service.getPort());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        jsonResponse.addProperty("respuestas", serviceCount);
        return jsonResponse;
    }

    private JsonObject ejecutarService(JsonObject jsonRequest) {
        String requestedService = jsonRequest.get("valor1").getAsString();

        switch (requestedService) {
            case "contar":
                return routeContarRequest(jsonRequest);
            case "votar":
                return routeVotarRequest(jsonRequest);
            case "registrar":
                return routeRegistrarRequest(jsonRequest);
            case "listar":
                return routeListarRequest(jsonRequest);
            default:
                JsonObject jsonResponse = new JsonObject();
                jsonResponse.addProperty("error", "Invalid service");
                return jsonResponse;
        }
    }

    // Reenvio de requests de ejecutar a servicio pertinente

    private JsonObject routeContarRequest(JsonObject jsonRequest) {
        Service service = getServiceFromName("contar");

        JsonObject jsonResponse = requestSender.contarRequest(service);
        return jsonResponse;
    }

    private JsonObject routeVotarRequest(JsonObject jsonRequest) {
        Service service = getServiceFromName("votar");
        String candidateName = jsonRequest.get("variable2").getAsString();
        int voteCount = jsonRequest.get("valor2").getAsInt();

        JsonObject jsonResponse = requestSender.votarRequest(service, candidateName, voteCount);
        return jsonResponse;
    }

    private JsonObject routeRegistrarRequest(JsonObject jsonRequest) {
        Service service = getServiceFromName("registrar");
        String event = jsonRequest.get("valor2").getAsString();
        String fecha = jsonRequest.get("valor3").getAsString();

        JsonObject jsonResponse = requestSender.registrarRequest(service, event, fecha);
        return jsonResponse;
    }

    private JsonObject routeListarRequest(JsonObject jsonRequest) {
        Service service = getServiceFromName("listar");

        JsonObject jsonResponse = requestSender.listarRequest(service);
        return jsonResponse;
    }

    private Service getServiceFromName(String name) {
        Service service = null;
        int i = 0;
        while (service == null) {
            if (services.get(i).getServiceName().equals(name)) {
                service = services.get(i);
            }
            i++;
        }

        return service;
    }
}
