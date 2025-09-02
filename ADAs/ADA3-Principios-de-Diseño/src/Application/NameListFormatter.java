package Application;

import java.util.ArrayList;
import java.util.Collections;

public class NameListFormatter {
    public static void main(String[] args) throws Exception {
        TextFileReader fileReader = new TextFileReader();
        String path = fileReader.getFilePath();
        ArrayList<String> names = fileReader.ReadFile(path);

        NameParser nameParser = new NameParser();
        names = nameParser.fixListCapitalization(names);

        Collections.sort(names);

        for (String name : names) {
            System.out.println(name);
        }
    }
}
