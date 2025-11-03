package com.god.server;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class BrokerConnection {
    private String brokerIP;
    private int brokerPort;
    private ServerSocket serverSocket;
    private boolean isRunning;
    private Thread serverThread;
    RequestProcessor requestProcessor;

    public BrokerConnection(int serverPort, String brokerIP, int brokerPort)
            throws IOException {
        this.requestProcessor = new RequestProcessor();
        this.brokerIP = brokerIP;
        this.brokerPort = brokerPort;
        startServer(serverPort);
        registerToBroker(serverPort, brokerIP, brokerPort);
    }

    private void registerToBroker(int serverPort, String brokerIP, int brokerPort) throws IOException {
        System.out.println("Connected to broker at " + brokerIP + ":" + brokerPort);
        sendRequest(makeInitialMessage(serverPort, "contar"));
        sendRequest(makeInitialMessage(serverPort, "votar"));
        sendRequest(makeInitialMessage(serverPort, "registrar"));
        sendRequest(makeInitialMessage(serverPort, "listar"));
    }

    private void sendRequest(String request) throws IOException {
        Socket brokerSocket = new Socket(brokerIP, brokerPort);
        PrintWriter out = new PrintWriter(brokerSocket.getOutputStream(), true);
        out.println(request);

        BufferedReader in = new BufferedReader(new InputStreamReader(brokerSocket.getInputStream()));
        String response = in.readLine();

        out.close();
        in.close();
        brokerSocket.close();
        System.out.println(response);
    }

    private String makeInitialMessage(int serverPort, String serviceName) throws UnknownHostException {
        Gson gson = new Gson();
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("servicio", "registrar");
        jsonMap.put("variables", 4);
        jsonMap.put("variable1", "servidor");
        jsonMap.put("valor1", InetAddress.getLocalHost().getHostAddress());
        jsonMap.put("variable2", "puerto");
        jsonMap.put("valor2", serverPort);
        jsonMap.put("variable3", "servicio");
        jsonMap.put("valor3", serviceName);
        jsonMap.put("variable4", "parametros");
        jsonMap.put("valor4", 1);
        return gson.toJson(jsonMap);
    }

    private void startServer(int port) throws IOException {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                isRunning = true;
                System.out.println("Server is running and waiting for broker requests on port " + port + "..");

                while (isRunning) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Broker request received!");

                    Thread clientThread = new Thread(() -> handleRequest(clientSocket));
                    clientThread.setDaemon(true); // Make it a daemon thread
                    clientThread.start();
                }
            } catch (IOException e) {
                if (isRunning) {
                    System.err.println("Server error: " + e.getMessage());
                }
            }
        }).start();
    }

    private void handleRequest(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message = in.readLine();
            if (message != null) {
                System.out.println("Request: " + message);

                String response = requestProcessor.processRequest(message);

                out.println(response);
                System.out.println("Response: " + response);
            }
        } catch (IOException e) {
            System.err.println("Error handling request: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
    }

    public void close() throws IOException {
        isRunning = false;
        if (serverThread != null) {
            serverThread.interrupt();
        }
        if (serverSocket != null) {
            serverSocket.close();
        }
    }
}