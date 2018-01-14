package visualization;

import org.junit.Test;
import patch.Patch;
import patch.PatchString;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class InfoWriterTest {
    @Test
    public void wrap() throws Exception {
        ArrayList<PatchString> patchStrings = new ArrayList<PatchString>();
        Patch patch = new Patch("1", "+", "213", patchStrings);
        InfoWriter infoWriter = new InfoWriter(patch, 0);
        ArrayList<String> template = new ArrayList<>();

        infoWriter.wrap(template);

    }

}