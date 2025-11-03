package com.god.broker;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Indique el puerto de escucha del broker: ");
        String port = scanner.nextLine();

        try {
            RequestReceiver receiver = new RequestReceiver(Integer.parseInt(port));
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}