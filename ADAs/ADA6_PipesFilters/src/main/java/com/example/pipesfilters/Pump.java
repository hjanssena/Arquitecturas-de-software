package com.example.pipesfilters;

import java.util.LinkedList;
import java.util.Queue;

public class Pump {
    Queue<Filter> filters;

    Pump() {
        filters = new LinkedList<Filter>();
    }

    Object run(Object input) {
        Object output = input;
        for (Filter filter : filters) {
            output = filter.process(output);
        }
        return output;
    }

    void addFilter(Filter filter) {
        filters.add(filter);
    }
}
