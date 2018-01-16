package patch;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class BasicBasicPatchParserTest {
    @Test
    public void parsePatch() throws Exception {
        BasicPatchParser basicPatchParser = new BasicPatchParser("src/input/HelloWorld.patch");

        basicPatchParser.parsePatch();
    }

    @Test(expected = IOException.class)
    public void parsePatchWithErrorInUserNameString() throws Exception {
        BasicPatchParser basicPatchParser = new BasicPatchParser("src/input/test.patch");

        basicPatchParser.parsePatch();
    }

    @Test(expected = IllegalArgumentException.class)
    public void parsePatchWithErrorInNumberString1() throws Exception {
        BasicPatchParser basicPatchParser = new BasicPatchParser("src/input/test1.patch");

        basicPatchParser.parsePatch();
    }

    @Test(expected = IllegalArgumentException.class)
    public void parsePatchWithErrorInNumberString2() throws Exception {
        BasicPatchParser basicPatchParser = new BasicPatchParser("src/input/test2.patch");

        basicPatchParser.parsePatch();
    }

    @Test
    public void parsePatchWithTwoNumberStrings() throws Exception {
        BasicPatchParser basicPatchParser = new BasicPatchParser("src/input/Game.patch");

        basicPatchParser.parsePatch();
    }

    @Test(expected = NoSuchFileException.class)
    public void parsePatchWithErrorInFileName() throws Exception {
        BasicPatchParser basicPatchParser = new BasicPatchParser("src/input/Ge.patch");

        basicPatchParser.parsePatch();
    }

    @Test(expected = IOException.class)
    public void parsePatchWithoutStringsBlock() throws Exception {
        BasicPatchParser basicPatchParser = new BasicPatchParser("src/input/test3.patch");

        basicPatchParser.parsePatch();
    }
}