package visualization;

import patch.PatchString;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasicSplitWriter implements DataWriter {
    private List<PatchString> addedStrings = new ArrayList<>();
    private List<PatchString> deletedStrings = new ArrayList<>();
    private List<String> templateData = new ArrayList<>();

    public BasicSplitWriter(List<List<PatchString>> data, List<String> template) {
        addedStrings = data.get(0);
        deletedStrings = data.get(1);
        templateData = template;
    }

    public void generateHtml(String fileName) throws IOException {
        BasicStringWrapper wrapper = new BasicStringWrapper();
        Integer firstTablePosition = 32;
        Integer secondTablePosition = 36;

        for (int i = addedStrings.size() - 1; i >= 0; i--) {
            templateData.add(firstTablePosition, wrapper.wrap(addedStrings.get(i)));
            secondTablePosition++;
        }

        for (int i = deletedStrings.size() - 1; i >= 0; i--) {
            templateData.add(secondTablePosition, wrapper.wrap(deletedStrings.get(i)));
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false));

        for (String str : templateData) {
            bw.write(str);
        }
        bw.close();
    }
}
