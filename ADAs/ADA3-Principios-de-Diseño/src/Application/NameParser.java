package Application;

import java.util.ArrayList;

public class NameParser {
    public ArrayList<String> fixListCapitalization(ArrayList<String> unprocessedNames) {
        ArrayList<String> processedNames = new ArrayList<String>();
        for (String name : unprocessedNames) {
            processedNames.add(capitalizeName(name));
        }
        return processedNames;
    }

    private String capitalizeName(String name) {
        name = name.toLowerCase();
        char[] cName = name.toCharArray();
        boolean isFirstLetter = true;
        int i = 0;
        for (char c : cName) {
            if (isFirstLetter) {
                c = Character.toUpperCase(c);
                isFirstLetter = false;
            }
            if (c == ' ') {
                isFirstLetter = true;
            }
            cName[i] = c;
            i++;
        }
        return new String(cName);
    }
}
