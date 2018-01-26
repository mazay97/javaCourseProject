package visualization;

import patch.PatchString;
import pojo.MergedData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasicSplitWriter implements DataWriter {
    private List<PatchString> mAddedStrings = new ArrayList<>();
    private List<PatchString> mDeletedStrings = new ArrayList<>();
    private List<String> mTemplateData = new ArrayList<>();

    public BasicSplitWriter(MergedData data, List<String> template) {
        mAddedStrings = data.getAdded();
        mDeletedStrings = data.getDeleted();
        mTemplateData = template;
    }

    public void generateHtml(String fileName) throws IOException {
        BasicStringWrapper wrapper = new BasicStringWrapper();
        Integer firstTablePosition = 32;
        Integer secondTablePosition = 36;

        for (int i = mAddedStrings.size() - 1; i >= 0; i--) {
            mTemplateData.add(firstTablePosition, wrapper.wrap(mAddedStrings.get(i)));
            secondTablePosition++;
        }

        for (int i = mDeletedStrings.size() - 1; i >= 0; i--) {
            mTemplateData.add(secondTablePosition, wrapper.wrap(mDeletedStrings.get(i)));
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false));

        for (String str : mTemplateData) {
            bw.write(str);
        }
        bw.close();
    }
}
