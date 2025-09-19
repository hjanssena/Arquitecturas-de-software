package DomainLayer;

import java.util.ArrayList;
import java.util.Collections;

import PersistenceLayer.EmptyWordList;

public class KwicAlgorithm {
    public ArrayList<String> getGeneratedIndexes(String inputSentence) {
        String sentenceInLower = inputSentence.toLowerCase();
        ArrayList<String> words = getWordsFromSentence(sentenceInLower);
        words = getListWithoutEmptyWords(words);
        ArrayList<String> sentences = getSentenceVariations(words);
        Collections.sort(sentences);
        return sentences;
    }

    private ArrayList<String> getWordsFromSentence(String sentence) {
        ArrayList<String> words = new ArrayList<String>();
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

    private ArrayList<String> getListWithoutEmptyWords(ArrayList<String> words) {
        EmptyWordList emptyWords = new EmptyWordList();
        int i = 0;
        while (i < words.size()) {
            if (emptyWords.getList().contains(words.get(i))) {
                words.remove(i);
            } else {
                i++;
            }
        }
        return words;
    }

    private ArrayList<String> getSentenceVariations(ArrayList<String> words) {
        // Conseguimos la primera sentencia
        String firstSentence = "";
        for (String word : words) {
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
            for (String word : words) {
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
