package com.god.server;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int serverPort, brokerPort;
        String brokerIP;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el IP del broker:");
        brokerIP = scanner.nextLine();
        System.out.println("Introduzca el puerto del broker:");
        brokerPort = Integer.parseInt(scanner.nextLine());
        System.out.println("Introduzca el puerto en el que va a escuchar este server:");
        serverPort = Integer.parseInt(scanner.nextLine());

        scanner.close();

        try {
            BrokerConnection brokerConnection = new BrokerConnection(serverPort, brokerIP, brokerPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}