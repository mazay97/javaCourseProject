package visualization;

import patch.BasicPatchString;
import patch.Patch;
import patch.PatchString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicDataMerger implements DataMerger {
    private List<PatchString> mPatchData = new ArrayList<>();
    private Map<Integer, String> mFileData = new HashMap<>();

    public BasicDataMerger(List<PatchString> patchStrings, Map<Integer, String> file){
        mPatchData = patchStrings;
        mFileData = file;
    }

    public List<List<PatchString>> getMergedData() {
        List<List<PatchString>> result = new ArrayList<>();
        List<PatchString> addedResult = new ArrayList<>();
        List<PatchString> deletedResult = new ArrayList<>();

        fillFirstTable(deletedResult, addedResult);
        fillSecondTable(addedResult);

        result.add(deletedResult);
        result.add(addedResult);

        return result;
    }

    private void fillFirstTable(List<PatchString> result, List<PatchString> delResult){

        for (int i = 0; i < mFileData.size(); i++){
            result.add(new BasicPatchString(i + 1, ' ', mFileData.get(i)));
            delResult.add(new BasicPatchString(i + 1, ' ', mFileData.get(i)));
        }

        for (int i = 0; i <= result.size(); i++){
            for (PatchString aPatchData : mPatchData) {
                if (aPatchData.getDeletedStringNumber() == i && aPatchData.getStatus() == '-') {
                    result.set(i - 1, aPatchData);
                    delResult.set(i - 1, new BasicPatchString(aPatchData));
                }
            }
        }
    }


    private void fillSecondTable(List<PatchString> result){

        for (int i = 0; i < result.size(); i++){
            if (result.get(i).getStatus() == '-'){
                updateDelIndex(result, i);
                result.remove(i);
                i--;
            }
        }

        for (int i = 0; i < mFileData.size(); i++){
            for (PatchString aPatchData : mPatchData) {
                if (aPatchData.getStatus() == '+' && aPatchData.getAddedStringNumber() == i) {
                    updateAddIndex(result, i - 1);
                    result.add(i - 1, aPatchData);
                }
            }
        }


    }

    private void updateAddIndex(List<PatchString> result, int startIndex){
        Integer currentStringNumber;
        for (int i = startIndex; i < result.size(); i++){
            currentStringNumber = result.get(i).getDeletedStringNumber();
            currentStringNumber++;
            result.get(i).setDeletedStringNumber(currentStringNumber);
        }
    }

    private void updateDelIndex(List<PatchString> result, int startIndex){
        Integer currentStringNumber;
        for (int i = startIndex; i < result.size(); i++){
            currentStringNumber = result.get(i).getDeletedStringNumber();
            currentStringNumber--;
            result.get(i).setDeletedStringNumber(currentStringNumber);
        }
    }
}
