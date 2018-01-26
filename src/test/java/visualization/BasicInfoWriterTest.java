package visualization;

import org.junit.Test;
import patch.BasicPatch;
import patch.PatchString;

import java.util.ArrayList;
import java.util.List;

public class BasicInfoWriterTest {
    @Test
    public void wrap() {
        List<PatchString> patchStrings = new ArrayList<>();
        BasicPatch basicPatch = new BasicPatch("1", "+", "213", patchStrings);
        BasicInfoWriter infoWriter = new BasicInfoWriter(basicPatch, 0);
        List<String> template = new ArrayList<>();

        infoWriter.wrap(template);

    }

}