package PresentationLayer;

import java.util.Scanner;

import ApplicationLayer.KwicService;

public class TerminalView {
    public static void main(String[] args) throws Exception {
        System.out.println("Introduce una sentencia: ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        KwicService kService = new KwicService();
        System.out.println("\nResultado:\n" + kService.getIndexes(userInput));
        scanner.close();
    }
}
