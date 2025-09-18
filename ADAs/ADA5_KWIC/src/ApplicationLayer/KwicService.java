package ApplicationLayer;

import java.util.ArrayList;

import DomainLayer.KwicAlgorithm;

public class KwicService {
    public String getIndexes(String userInput) {
        KwicAlgorithm kwic = new KwicAlgorithm();
        ArrayList<String> indexes = kwic.getGeneratedIndexes(userInput);
        return getFormattedOutput(indexes);
    }

    private String getFormattedOutput(ArrayList<String> indexes) {
        String output = "";
        for (String index : indexes) {
            output += index + "\n";
        }
        return output;
    }
}
