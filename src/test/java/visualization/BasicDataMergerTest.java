package visualization;

import org.junit.Test;
import patch.BasicPatchString;
import patch.PatchString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class BasicDataMergerTest {
    @Test
    public void getMergedData() throws Exception {
        List<PatchString> patchStrings = new ArrayList<>();
        HashMap<Integer, String> file = new HashMap<>();

        patchStrings.add(new BasicPatchString(1, '+', "123"));
        patchStrings.add(new BasicPatchString(2, '-', "123"));
        patchStrings.add(new BasicPatchString(3, '+', "123"));

        file.put(0, "");
        file.put(1, "123");
        file.put(2, "");

        DataMerger dataMerger = new BasicDataMerger(patchStrings, file);
        List<List<PatchString>> data = dataMerger.getMergedData();
        assertEquals(2, data.size());
        assertEquals(3, data.get(0).size());
        assertEquals(3, data.get(1).size());

    }

    @Test
    public void getMergedDataFromEmptyFileAndPatch() throws Exception {
        List<PatchString> patchStrings = new ArrayList<>();
        HashMap<Integer, String> file = new HashMap<>();


        DataMerger dataMerger = new BasicDataMerger(patchStrings, file);
        List<List<PatchString>> data = dataMerger.getMergedData();
        assertEquals(2, data.size());
        assertEquals(0, data.get(0).size());
        assertEquals(0, data.get(1).size());

    }
}