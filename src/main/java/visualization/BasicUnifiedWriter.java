package visualization;

import patch.BasicPatchString;
import patch.PatchString;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasicUnifiedWriter implements DataWriter {
    private List<PatchString> mAddedStrings = new ArrayList<>();
    private List<PatchString> mDeletedStrings = new ArrayList<>();
    private List<String> mTemplateData = new ArrayList<>();
    private StringWrapper wrapper = new BasicStringWrapper();
    private static final Integer POSITION = 24;

    public BasicUnifiedWriter(List<List<PatchString>> data, List<String> template){
        mAddedStrings = data.get(1);
        mDeletedStrings = data.get(0);
        mTemplateData = template;
    }

    public void generateHtml(String fileName) throws IOException {

        List<PatchString> result = mergeStrings();

        for (int i = 0; i < result.size(); i++){
            mTemplateData.add(POSITION + i, wrapper.wrap(result.get(i), result.get(i).getAddedStringNumber()));
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,false));

        for (String str: mTemplateData){
            bw.write(str);
        }
        bw.close();
    }

    private List<PatchString> mergeStrings(){
        List<PatchString> result = new ArrayList<>();
        PatchString currentPatchStr;

        for (int i = 0; i < mDeletedStrings.size(); i++){
            currentPatchStr = mDeletedStrings.get(i);
            if (currentPatchStr.getStatus() == '-'){
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

    private void addAddedStrings(List<PatchString> result){
        PatchString currentPatchStr;
        Integer addedStrNum = 0;
        for (int i = 0; i < mAddedStrings.size() - 1; i++){
            currentPatchStr = mAddedStrings.get(i);
            if (currentPatchStr.getStatus() == '-'){
                continue;
            }
            if (result.size() <= i){
                result.add(i, currentPatchStr);
            } else {
                if (mAddedStrings.get(i).getStatus() == '+'){
                    currentPatchStr.setDeletedStringNumber(i + 1);
                    result.add(currentPatchStr.getAddedStringNumber() - 1 + addedStrNum, currentPatchStr);
                    if (mAddedStrings.get(i + 1).getStatus() != '+'){
                        addedStrNum++;
                    }
                }
            }
        }
    }

    private void updateIndexes(List<PatchString> result){
        Integer addedStrIndex = 0;

        for (PatchString aResult : result) {
            if (aResult.getStatus() == '+') {
                addedStrIndex++;
            } else if (aResult.getStatus() == '-') {
                continue;
            } else {
                addedStrIndex++;
            }
            aResult.setDeletedStringNumber(addedStrIndex);
        }
    }

}


