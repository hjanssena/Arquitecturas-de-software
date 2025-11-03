package com.god.broker;

import java.io.*;
import java.net.*;

public class RequestReceiver {
    private ServerSocket brokerSocket;
    private boolean isRunning;
    private Thread serverThread;
    RequestProcessor requestProcessor;

    public RequestReceiver(int port) throws IOException {
        this.requestProcessor = new RequestProcessor();
        startBroker(port);
    }

    private void startBroker(int port) throws IOException {
        new Thread(() -> {
            try {
                brokerSocket = new ServerSocket(port);
                isRunning = true;
                System.out.println("Server is running and waiting for broker requests on port " + port + "..");

                while (isRunning) {
                    Socket clientSocket = brokerSocket.accept();
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
        if (brokerSocket != null) {
            brokerSocket.close();
        }
        if (brokerSocket != null) {
            brokerSocket.close();
        }
    }
}