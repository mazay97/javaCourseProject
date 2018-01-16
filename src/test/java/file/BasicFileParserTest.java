package file;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class BasicFileParserTest {
    @Test
    public void parse() throws Exception {
        Map<Integer, String> result;
        BasicFileParser basicFileParser = new BasicFileParser("src/input/Game.cpp");

        result = basicFileParser.parse();

        assertEquals(542, result.size());
    }

    @Test(expected = Exception.class)
    public void parseNotFile() throws Exception {
        Map<Integer, String> result;
        BasicFileParser basicFileParser = new BasicFileParser("src/input.cpp");

        result = basicFileParser.parse();
    }
}