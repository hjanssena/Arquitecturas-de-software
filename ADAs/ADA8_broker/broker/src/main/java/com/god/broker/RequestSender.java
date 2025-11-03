package com.god.broker;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class RequestSender {

    public JsonObject contarRequest(Service service) {
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("servicio", "contar");
        jsonRequest.addProperty("variables", 0);

        JsonObject serverResponse = sendRequestToService(service, jsonRequest);

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("servicio", "ejecutar");
        jsonResponse.addProperty("respuesta1", "servicio");
        jsonResponse.addProperty("valor1", "contar");

        int i = 1;
        while (serverResponse.has("respuesta" + i)) {
            i++;
            jsonResponse.addProperty("respuesta" + i, serverResponse.get("respuesta" + (i - 1)).getAsString());
            jsonResponse.addProperty("valor" + i, serverResponse.get("valor" + (i - 1)).getAsInt());
        }
        jsonResponse.addProperty("respuestas", i);
        return jsonResponse;
    }

    public JsonObject votarRequest(Service service, String candidateName, int voteCount) {
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("servicio", "votar");
        jsonRequest.addProperty("variables", 1);
        jsonRequest.addProperty("variable1", candidateName);
        jsonRequest.addProperty("valor1", voteCount);

        JsonObject serverResponse = sendRequestToService(service, jsonRequest);
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("servicio", "ejecutar");
        jsonResponse.addProperty("respuestas", 2);
        jsonResponse.addProperty("respuesta1", "servicio");
        jsonResponse.addProperty("valor1", "votar");
        jsonResponse.addProperty("respuesta2", serverResponse.get("respuesta1").getAsString());
        jsonResponse.addProperty("valor2", serverResponse.get("valor1").getAsInt());

        return jsonResponse;
    }

    public JsonObject registrarRequest(Service service, String event, String fecha) {
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("servicio", "registrar");
        jsonRequest.addProperty("variables", 2);
        jsonRequest.addProperty("variable1", "evento");
        jsonRequest.addProperty("valor1", event);
        jsonRequest.addProperty("variable2", "fecha");
        jsonRequest.addProperty("valor2", fecha);

        JsonObject serverResponse = sendRequestToService(service, jsonRequest);
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("servicio", "ejecutar");
        jsonResponse.addProperty("respuestas", 2);
        jsonResponse.addProperty("respuesta1", "servicio");
        jsonResponse.addProperty("valor1", "registrar");
        jsonResponse.addProperty("respuesta2", serverResponse.get("respuesta1").getAsString());
        jsonResponse.addProperty("valor2", serverResponse.get("valor1").getAsInt());

        return jsonResponse;
    }

    public JsonObject listarRequest(Service service) {
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("servicio", "listar");
        jsonRequest.addProperty("variables", 0);

        JsonObject serverResponse = sendRequestToService(service, jsonRequest);

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("servicio", "ejecutar");
        jsonResponse.addProperty("respuesta1", "servicio");
        jsonResponse.addProperty("valor1", "listar");

        int i = 1;
        while (serverResponse.has("respuesta" + i)) {
            i++;
            jsonResponse.addProperty("respuesta" + i, serverResponse.get("respuesta" + (i - 1)).getAsString());
            jsonResponse.addProperty("valor" + i, serverResponse.get("valor" + (i - 1)).getAsString());
        }
        jsonResponse.addProperty("respuestas", i);

        return jsonResponse;
    }

    private JsonObject sendRequestToService(Service service, JsonObject jsonRequest) {
        try {
            String response = service.sendRequest(jsonRequest.toString());
            JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
            return jsonResponse;
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }

        return new JsonObject();
    }
}
