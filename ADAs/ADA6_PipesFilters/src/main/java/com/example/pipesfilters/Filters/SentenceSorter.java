package com.example.pipesfilters.Filters;

import java.util.Collections;
import java.util.List;

import com.example.pipesfilters.Filter;

public class SentenceSorter implements Filter {

    @Override
    public Object process(Object input) {
        List<String> sentences = (List<String>) input;
        Collections.sort(sentences);
        return sentences;
    }
}
