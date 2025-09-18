package PersistenceLayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EmptyWordList {
    private ArrayList<String> emptyWords;

    public EmptyWordList() {
        emptyWords = readFile("spanish.txt");
    }

    public ArrayList<String> getList() {
        return emptyWords;
    }

    private ArrayList<String> readFile(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            ArrayList<String> words = new ArrayList<String>();
            String line = br.readLine();

            while (line != null) {
                words.add(line);
                line = br.readLine();
            }
            br.close();
            return words;
        } catch (IOException e) {
            System.out.println("Fallo algo en readFile");
            return new ArrayList<String>();
        }
    }
}
