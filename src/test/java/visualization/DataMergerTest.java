package visualization;

import org.junit.Test;
import patch.PatchString;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class DataMergerTest {
    @Test
    public void getMergedData() throws Exception {
        ArrayList<PatchString> patchStrings = new ArrayList<>();
        HashMap<Integer, String> file = new HashMap<>();

        patchStrings.add(new PatchString(1, '+', "123"));
        patchStrings.add(new PatchString(2, '-', "123"));
        patchStrings.add(new PatchString(3, '+', "123"));

        file.put(0, "");
        file.put(1, "123");
        file.put(2, "");

        DataMerger dataMerger = new DataMerger(patchStrings, file);
        ArrayList<ArrayList<PatchString>> data = dataMerger.getMergedData();
        assertEquals(2, data.size());
        assertEquals(3, data.get(0).size());
        assertEquals(3, data.get(1).size());

    }

    @Test
    public void getMergedDataFromEmptyFileAndPatch() throws Exception {
        ArrayList<PatchString> patchStrings = new ArrayList<>();
        HashMap<Integer, String> file = new HashMap<>();


        DataMerger dataMerger = new DataMerger(patchStrings, file);
        ArrayList<ArrayList<PatchString>> data = dataMerger.getMergedData();
        assertEquals(2, data.size());
        assertEquals(0, data.get(0).size());
        assertEquals(0, data.get(1).size());

    }
}