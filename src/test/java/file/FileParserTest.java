package file;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class FileParserTest {
    @Test
    public void parse() throws Exception {
        HashMap<Integer, String> result;
        FileParser fileParser = new FileParser("src/input/Game.cpp");

        result = fileParser.parse();

        assertEquals(542, result.size());
    }

    @Test(expected = Exception.class)
    public void parseNotFile() throws Exception {
        HashMap<Integer, String> result;
        FileParser fileParser = new FileParser("src/input.cpp");

        result = fileParser.parse();
    }
}