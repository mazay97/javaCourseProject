package visualization;

import org.junit.Test;
import patch.BasicPatchString;
import patch.PatchString;
import pojo.MergedData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BasicDataMergerTest {
    @Test(expected = IndexOutOfBoundsException.class)
    public void getMergedData() {
        List<PatchString> patchStrings = new ArrayList<>();
        HashMap<Integer, String> file = new HashMap<>();

        patchStrings.add(new BasicPatchString(1, '+', "123"));
        patchStrings.add(new BasicPatchString(2, '-', "123"));
        patchStrings.add(new BasicPatchString(3, '+', "123"));

        file.put(0, "");
        file.put(1, "123");
        file.put(2, "");

        DataMerger dataMerger = new BasicDataMerger(patchStrings, file);
        MergedData data = dataMerger.getMergedData();
        assertEquals(3, data.getAdded().size());
        assertEquals(3, data.getDeleted().size());

    }

    @Test
    public void getMergedDataFromEmptyFileAndPatch() {
        List<PatchString> patchStrings = new ArrayList<>();
        HashMap<Integer, String> file = new HashMap<>();

        DataMerger dataMerger = new BasicDataMerger(patchStrings, file);
        MergedData data = dataMerger.getMergedData();
        assertEquals(0, data.getAdded().size());
        assertEquals(0, data.getDeleted().size());

    }
}