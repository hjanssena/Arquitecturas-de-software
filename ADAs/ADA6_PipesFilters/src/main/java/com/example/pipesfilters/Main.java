package com.example.pipesfilters;

import java.util.Scanner;

import com.example.pipesfilters.Filters.EmptyWordRemover;
import com.example.pipesfilters.Filters.KwicSentencesGenerator;
import com.example.pipesfilters.Filters.LowercaseParser;
import com.example.pipesfilters.Filters.SentenceSorter;
import com.example.pipesfilters.Filters.TerminalPrinter;
import com.example.pipesfilters.Filters.WordParser;

public class Main {
    public static void main(String[] args) {
        Pump pump = new Pump();
        pump.addFilter(new WordParser());
        pump.addFilter(new EmptyWordRemover());
        pump.addFilter(new KwicSentencesGenerator());
        pump.addFilter(new LowercaseParser());
        pump.addFilter(new SentenceSorter());
        pump.addFilter(new TerminalPrinter());

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        scanner.close();

        pump.run(userInput);
    }
}