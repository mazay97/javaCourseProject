import file.BasicFileParser;
import org.junit.Test;
import patch.BasicPatch;
import patch.BasicPatchParser;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;

public class BasicFileAndPatchComparatorTest {

    @Test
    public void compareNormalFileAndPatch() {
        try{
            BasicFileParser fileParser = new BasicFileParser("src/input/HelloWorld.java");
            Map<Integer, String> file = fileParser.parse();
            BasicPatchParser patchParser = new BasicPatchParser("src/input/HelloWorld.patch");
            BasicPatch patch = patchParser.parsePatch();
            Comparator comparator = new BasicFileAndPatchComparator(patch, file, "HelloWorld.java");
            comparator.compare();
        } catch (IOException ex) {
            System.out.println("err");
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void compareDifferentFileAndPatch() {
        try{
            BasicFileParser fileParser = new BasicFileParser("src/input/HelloWorld.java");
            Map<Integer, String> file = fileParser.parse();
            BasicPatchParser patchParser = new BasicPatchParser("src/input/rules.patch");
            BasicPatch patch = patchParser.parsePatch();
            Comparator comparator = new BasicFileAndPatchComparator(patch, file, "HelloWorld.java");
            comparator.compare();
        } catch (IOException ex) {
            System.out.println("err");
        }
    }

}