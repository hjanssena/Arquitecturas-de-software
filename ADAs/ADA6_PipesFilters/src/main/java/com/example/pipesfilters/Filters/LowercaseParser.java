package com.example.pipesfilters.Filters;

import java.util.List;

import com.example.pipesfilters.Filter;

public class LowercaseParser implements Filter {

    @Override
    public Object process(Object input) {
        List<String> sentences = (List<String>) input;
        for (String sentence : sentences) {
            sentence = sentence.toLowerCase();
        }
        return sentences;
    }
}
