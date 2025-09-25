package com.example.pipesfilters.Filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.pipesfilters.Filter;

public class EmptyWordRemover implements Filter {

    private List<String> emptyWords;

    public EmptyWordRemover() {
        emptyWords = readFile("spanish.txt");
    }

    @Override
    public Object process(Object input) {
        List<String> words = (List<String>) input;
        int i = 0;
        while (i < words.size()) {
            if (emptyWords.contains(words.get(i))) {
                words.remove(i);
            } else {
                i++;
            }
        }
        return words;
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
