package patch;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.junit.Assert.*;

public class PatchParserTest {
    @Test
    public void parsePatch() throws Exception {
        PatchParser patchParser = new PatchParser("src/input/HelloWorld.patch");

        patchParser.parsePatch();
    }

    @Test(expected = IOException.class)
    public void parsePatchWithErrorInUserNameString() throws Exception {
        PatchParser patchParser = new PatchParser("src/input/test.patch");

        patchParser.parsePatch();
    }

    @Test(expected = IllegalArgumentException.class)
    public void parsePatchWithErrorInNumberString1() throws Exception {
        PatchParser patchParser = new PatchParser("src/input/test1.patch");

        patchParser.parsePatch();
    }

    @Test(expected = IllegalArgumentException.class)
    public void parsePatchWithErrorInNumberString2() throws Exception {
        PatchParser patchParser = new PatchParser("src/input/test2.patch");

        patchParser.parsePatch();
    }

    @Test
    public void parsePatchWithTwoNumberStrings() throws Exception {
        PatchParser patchParser = new PatchParser("src/input/Game.patch");

        patchParser.parsePatch();
    }

    @Test(expected = NoSuchFileException.class)
    public void parsePatchWithErrorInFileName() throws Exception {
        PatchParser patchParser = new PatchParser("src/input/Ge.patch");

        patchParser.parsePatch();
    }

    @Test(expected = IOException.class)
    public void parsePatchWithoutStringsBlock() throws Exception {
        PatchParser patchParser = new PatchParser("src/input/test3.patch");

        patchParser.parsePatch();
    }
}