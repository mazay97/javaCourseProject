package visualization;

import patch.PatchString;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UnifiedWriter extends StringWrapper implements Writer {
    private ArrayList<PatchString> addedStrings = new ArrayList<>();
    private ArrayList<PatchString> deletedStrings = new ArrayList<>();
    private ArrayList<String> templateData = new ArrayList<>();

    private Integer position = 24;

    public UnifiedWriter(ArrayList<ArrayList<PatchString>> data, ArrayList<String> template){
        addedStrings = data.get(1);
        deletedStrings = data.get(0);
        templateData = template;
    }

    public void genHtml(String fileName) throws IOException {

        ArrayList<PatchString> result = mergeStrings();

        for (int i = 0; i < result.size(); i++){
            templateData.add(position + i, wrap(result.get(i), result.get(i).getAddedStringNumber()));
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,false));

        for (String str: templateData){
            bw.write(str);
        }
        bw.close();
    }

    private ArrayList<PatchString> mergeStrings(){
        ArrayList<PatchString> result = new ArrayList<>();
        PatchString currentPatchStr;

        for (int i = 0; i < deletedStrings.size(); i++){
            currentPatchStr = deletedStrings.get(i);
            if (currentPatchStr.getStatus() == '-'){
                currentPatchStr.setAddedStringNumber(0);
            } else {
                currentPatchStr.setAddedStringNumber(i + 1);
            }
            result.add(new PatchString(currentPatchStr));
        }

        addAddedStrings(result);
        updateIndexes(result);
        return result;
    }

    private void addAddedStrings(ArrayList<PatchString> result){
        PatchString currentPatchStr;
        for (int i = 0; i < addedStrings.size(); i++){
            currentPatchStr = addedStrings.get(i);
            if (currentPatchStr.getStatus() == '-'){
                continue;
            }
            if (result.size() <= i){
                result.add(i, currentPatchStr);
            } else {
                if (addedStrings.get(i).getStatus() == '+'){
                    currentPatchStr.setDeletedStringNumber(i + 1);
                    result.add(i, currentPatchStr);
                }
            }
        }
    }

    private void updateIndexes(ArrayList<PatchString> result){
        Integer addedStrIndex = 0;

        for (int i = 0; i < result.size(); i++){
            if (result.get(i).getStatus() == '+'){
                addedStrIndex++;
            } else if (result.get(i).getStatus() == '-'){
                continue;
            } else {
                addedStrIndex++;
            }
            result.get(i).setDeletedStringNumber(addedStrIndex);
        }
    }

}

//--file src/input/HelloWorld.java --patch src/input/HelloWorld.patch --out changes.html --mode unified
//--file src/input/Game.cpp --patch src/input/Game.patch --out changes.html --mode unified

