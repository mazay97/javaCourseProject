package patch;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PatchTest {
    @Test
    public void getName() throws Exception {
        Patch patch = new Patch();
        assertEquals("", patch.getName());
    }

    @Test
    public void getDateOfChange() throws Exception {
        ArrayList<PatchString> strings = new ArrayList<>();
        Patch patch = new Patch("name", "date", "file", strings);

        assertEquals("name", patch.getName());
        assertEquals("date", patch.getDateOfChange());
        assertEquals("file", patch.getFileName());
    }

    @Test
    public void getStrings() throws Exception {
        ArrayList<PatchString> strings = new ArrayList<>();
        strings.add(new PatchString(21, '+', "132"));
        Patch patch = new Patch("name", "date", "file", strings);

        assertEquals(1, patch.getStrings().size());
        patch.printPatch();
    }

}