package com.example.pipesfilters.Filters;

import java.util.List;

import com.example.pipesfilters.Filter;

public class TerminalPrinter implements Filter {

    @Override
    public Object process(Object input) {

        List<String> sentences = (List<String>) input;
        for (String sentence : sentences) {
            System.out.println(sentence);
        }
        return sentences;
    }
}
