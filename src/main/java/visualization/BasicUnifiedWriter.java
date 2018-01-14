package visualization;

import patch.BasicPatchString;
import patch.PatchString;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasicUnifiedWriter implements DataWriter {
    private List<PatchString> addedStrings = new ArrayList<>();
    private List<PatchString> deletedStrings = new ArrayList<>();
    private List<String> templateData = new ArrayList<>();

    private BasicStringWrapper wrapper = new BasicStringWrapper();
    private static final Integer POSITION = 24;

    public BasicUnifiedWriter(List<List<PatchString>> data, List<String> template) {
        addedStrings = data.get(1);
        deletedStrings = data.get(0);
        templateData = template;
    }

    public void generateHtml(String fileName) throws IOException {
        List<PatchString> result = mergeStrings();

        for (int i = 0; i < result.size(); i++) {
            templateData.add(POSITION + i, wrapper.wrap(result.get(i), result.get(i).getAddedStringNumber()));
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false));

        for (String str : templateData) {
            bw.write(str);
        }
        bw.close();
    }

    private List<PatchString> mergeStrings() {
        List<PatchString> result = new ArrayList<>();
        PatchString currentPatchStr;

        for (int i = 0; i < deletedStrings.size(); i++) {
            currentPatchStr = deletedStrings.get(i);
            if (currentPatchStr.getStatus() == '-') {
                currentPatchStr.setAddedStringNumber(0);
            } else {
                currentPatchStr.setAddedStringNumber(i + 1);
            }
            result.add(new BasicPatchString(currentPatchStr));
        }

        addAddedStrings(result);
        updateIndexes(result);
        return result;
    }

    private void addAddedStrings(List<PatchString> result) {
        PatchString currentPatchStr;
        for (int i = 0; i < addedStrings.size(); i++) {
            currentPatchStr = addedStrings.get(i);
            if (currentPatchStr.getStatus() == '-') {
                continue;
            }
            if (result.size() <= i) {
                result.add(i, currentPatchStr);
            } else {
                if (addedStrings.get(i).getStatus() == '+') {
                    currentPatchStr.setDeletedStringNumber(i + 1);
                    result.add(i, currentPatchStr);
                }
            }
        }
    }

    private void updateIndexes(List<PatchString> result) {
        Integer addedStrIndex = 0;

        for (PatchString patchString : result) {
            if (patchString.getStatus() == '+') {
                addedStrIndex++;
            } else if (patchString.getStatus() == '-') {
                continue;
            } else {
                addedStrIndex++;
            }
            patchString.setDeletedStringNumber(addedStrIndex);
        }
    }
}
