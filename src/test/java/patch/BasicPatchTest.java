package patch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BasicPatchTest {
    @Test
    public void getName() throws Exception {
        BasicPatch basicPatch = new BasicPatch();
        assertEquals("", basicPatch.getName());
    }

    @Test
    public void getDateOfChange() throws Exception {
        List<PatchString> strings = new ArrayList<>();
        BasicPatch basicPatch = new BasicPatch("name", "date", "file", strings);

        assertEquals("name", basicPatch.getName());
        assertEquals("date", basicPatch.getDateOfChange());
        assertEquals("file", basicPatch.getFileName());
    }

    @Test
    public void getStrings() throws Exception {
        List<PatchString> strings = new ArrayList<>();
        strings.add(new BasicPatchString(21, '+', "132"));
        BasicPatch basicPatch = new BasicPatch("name", "date", "file", strings);

        assertEquals(1, basicPatch.getStrings().size());
    }

}