package com.example.pipesfilters.Filters;

import java.util.ArrayList;
import java.util.List;

import com.example.pipesfilters.Filter;

public class WordParser implements Filter {

    @Override
    public Object process(Object input) {
        List<String> words = new ArrayList<String>();
        String sentence = (String) input;
        String word = "";
        for (char c : sentence.toCharArray()) {
            if (c == ' ') {
                words.add(word);
                word = "";
            } else {
                word += c;
            }
        }
        // Add last word if not empty
        if (word != "") {
            words.add(word);
        }
        return words;
    }
}
