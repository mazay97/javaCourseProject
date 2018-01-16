package visualization;

import org.junit.Test;
import patch.BasicPatch;
import patch.BasicPatchString;
import patch.PatchString;

import java.util.ArrayList;
import java.util.List;

public class BasicInfoWriterTest {
    @Test
    public void wrap() throws Exception {
        List<PatchString> patchStrings = new ArrayList<PatchString>();
        BasicPatch basicPatch = new BasicPatch("1", "+", "213", patchStrings);
        BasicInfoWriter infoWriter = new BasicInfoWriter(basicPatch, 0);
        ArrayList<String> template = new ArrayList<>();

        infoWriter.wrap(template);

    }

}