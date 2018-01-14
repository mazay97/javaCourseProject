package visualization;

import patch.PatchString;

import java.util.ArrayList;
import java.util.HashMap;

public class DataMerger {
    private ArrayList<PatchString> patchData = new ArrayList<>();
    private HashMap<Integer, String> fileData = new HashMap<>();

    public DataMerger(ArrayList<PatchString> patchStrings, HashMap<Integer, String> file){
        patchData = patchStrings;
        fileData = file;
    }

    public ArrayList<ArrayList<PatchString>> getMergedData() {
        ArrayList<ArrayList<PatchString>> result = new ArrayList<>();
        ArrayList<PatchString> addedResult = new ArrayList<>();
        ArrayList<PatchString> deletedResult = new ArrayList<>();

        fillFirstTable(addedResult, deletedResult);

        fillSecondTable(deletedResult);

        result.add(addedResult);
        result.add(deletedResult);

        return result;
    }

    private void fillFirstTable(ArrayList<PatchString> result, ArrayList<PatchString> delResult){

        for (int i = 0; i < fileData.size(); i++){
            result.add(new PatchString(i + 1, ' ', fileData.get(i)));
            delResult.add(new PatchString(i + 1, ' ', fileData.get(i)));
        }

        for (int i = 0; i <= result.size(); i++){
            for (int j = 0; j < patchData.size(); j++){
                if (patchData.get(j).getDeletedStringNumber() == i && patchData.get(j).getStatus() == '-'){
                    result.set(i - 1, patchData.get(j));
                    delResult.set(i - 1, new PatchString(patchData.get(j)));
                }
            }
        }
    }

    private void fillSecondTable(ArrayList<PatchString> result){

        for (int i = 0; i < fileData.size(); i++){
            for (int j = 0; j < patchData.size(); j++){
                if (patchData.get(j).getDeletedStringNumber() == i && patchData.get(j).getStatus() == '+'){
                    updateAddIndex(result, i - 1);
                    result.add(i - 1, patchData.get(j));
                }
            }
        }

        for (int i = 0; i < result.size(); i++){
            if (result.get(i).getStatus() == '-'){
                updateDelIndex(result, i);
                result.remove(i);
                i--;
            }
        }
    }

    private void updateAddIndex(ArrayList<PatchString> result, int startIndex){
        Integer currentStringNumber = 0;
        for (int i = startIndex; i < result.size(); i++){
            currentStringNumber = result.get(i).getDeletedStringNumber();
            currentStringNumber++;
            result.get(i).setDeletedStringNumber(currentStringNumber);
        }
    }

    private void updateDelIndex(ArrayList<PatchString> result, int startIndex){
        Integer currentStringNumber = 0;
        for (int i = startIndex; i < result.size(); i++){
            currentStringNumber = result.get(i).getDeletedStringNumber();
            currentStringNumber--;
            result.get(i).setDeletedStringNumber(currentStringNumber);
        }
    }
}
