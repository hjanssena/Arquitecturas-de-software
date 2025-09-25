package com.example.pipesfilters.Filters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.pipesfilters.Filter;

public class KwicSentencesGenerator implements Filter {

    @Override
    public Object process(Object input) {
        // Conseguimos la primera sentencia
        String firstSentence = "";
        List<String> words = (List<String>) input;
        for (Object word : words) {
            firstSentence += word + " ";
        }
        ArrayList<String> sentences = new ArrayList<String>();
        sentences.add(firstSentence);
        String newSentence = "";
        while (!newSentence.equals(firstSentence)) {
            // Rotamos la lista para que la primera palabra pase al final
            newSentence = "";
            Collections.rotate(words, -1);
            // Armamos la sentencia
            for (Object word : words) {
                newSentence += word + " ";
            }
            if (newSentence.equals(firstSentence)) {
                break;
            }
            sentences.add(newSentence);
        }
        return sentences;
    }
}
