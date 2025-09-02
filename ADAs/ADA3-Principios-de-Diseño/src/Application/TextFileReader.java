package Application;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TextFileReader {
    public String getFilePath() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce la ruta del archivo de texto a leer:");
        String path = scanner.nextLine();
        scanner.close();
        return path;
    }

    public ArrayList<String> ReadFile(String path) {
        try {
            BufferedReader bfro = new BufferedReader(new FileReader(path));
            ArrayList<String> nameList = new ArrayList<String>();
            String line;
            while ((line = bfro.readLine()) != null) {
                nameList.add(line);
            }
            bfro.close();
            return nameList;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado...");
            return new ArrayList<String>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<String>();
        }
    }
}
