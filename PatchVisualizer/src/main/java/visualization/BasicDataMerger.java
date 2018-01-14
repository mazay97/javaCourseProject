package visualization;

import patch.BasicPatchString;
import patch.PatchString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicDataMerger implements DataMerger {
    private List<PatchString> patchData = new ArrayList<>();
    private Map<Integer, String> fileData = new HashMap<>();

    public BasicDataMerger(List<PatchString> patchStrings, Map<Integer, String> file) {
        patchData = patchStrings;
        fileData = file;
    }

    public List<List<PatchString>> getMergedData() {
        List<List<PatchString>> result = new ArrayList<>();
        List<PatchString> addedResult = new ArrayList<>();
        List<PatchString> deletedResult = new ArrayList<>();

        fillFirstTable(addedResult, deletedResult);
        fillSecondTable(deletedResult);

        result.add(addedResult);
        result.add(deletedResult);

        return result;
    }

    private void fillFirstTable(List<PatchString> result, List<PatchString> delResult) {
        for (int i = 0; i < fileData.size(); i++) {
            result.add(new BasicPatchString(i + 1, ' ', fileData.get(i)));
            delResult.add(new BasicPatchString(i + 1, ' ', fileData.get(i)));
        }

        for (int i = 0; i <= result.size(); i++) {
            for (PatchString patchString : patchData) {
                if (patchString.getDeletedStringNumber() == i && patchString.getStatus() == '-') {
                    result.set(i - 1, patchString);
                    delResult.set(i - 1, new BasicPatchString(patchString));
                }
            }
        }
    }

    private void fillSecondTable(List<PatchString> result) {
        for (int i = 0; i < fileData.size(); i++) {
            for (PatchString patchString : patchData) {
                if (patchString.getDeletedStringNumber() == i && patchString.getStatus() == '+') {
                    updateAddIndex(result, i - 1);
                    result.add(i - 1, patchString);
                }
            }
        }

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getStatus() == '-') {
                updateDelIndex(result, i);
                result.remove(i);
                i--;
            }
        }
    }

    private void updateAddIndex(List<PatchString> result, int startIndex) {
        Integer currentStringNumber;
        for (int i = startIndex; i < result.size(); i++) {
            currentStringNumber = result.get(i).getDeletedStringNumber();
            currentStringNumber++;
            result.get(i).setDeletedStringNumber(currentStringNumber);
        }
    }

    private void updateDelIndex(List<PatchString> result, int startIndex) {
        Integer currentStringNumber;
        for (int i = startIndex; i < result.size(); i++) {
            currentStringNumber = result.get(i).getDeletedStringNumber();
            currentStringNumber--;
            result.get(i).setDeletedStringNumber(currentStringNumber);
        }
    }
}
