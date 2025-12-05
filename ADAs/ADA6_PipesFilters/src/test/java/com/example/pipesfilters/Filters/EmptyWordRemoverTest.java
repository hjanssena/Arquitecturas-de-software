package com.example.pipesfilters.Filters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmptyWordRemoverTest {
   
    @Test
    @DisplayName("removiendo palabras vacias de la lista")
    public void testRemoveEmptyWords() {
        EmptyWordRemover remover = new EmptyWordRemover();
        List<String> input = new ArrayList<>(Arrays.asList("este", "es", "un", "ejemplo", "de", "texto"));
        List<String> expectedOutput = Arrays.asList("ejemplo", "texto");
        List<String> actualOutput = (List<String>) remover.process(input);
        assertEquals(expectedOutput, actualOutput);
    }
}
