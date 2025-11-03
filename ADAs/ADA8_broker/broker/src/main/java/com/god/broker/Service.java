package com.god.broker;

import java.net.Socket;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Service {
    private String ipAddress;
    private int port;
    private String serviceName;
    private int numberOfParameters;

    public Service(String ipAddress, int port, String name, int params) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.serviceName = name;
        this.numberOfParameters = params;
    }

    public String sendRequest(String request) throws IOException {
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
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getNumberOfParameters() {
        return numberOfParameters;
    }

    public String getIpAddress() throws IOException {
        return ipAddress;
    }

    public int getPort() throws IOException {
        return port;
    }
}
