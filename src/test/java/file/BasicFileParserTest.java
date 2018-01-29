package file;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BasicFileParserTest {
    @Test
    public void parse() throws Exception {
        Map<Integer, String> result;
        BasicFileParser basicFileParser = new BasicFileParser("src/input/HelloWorld.java");

        result = basicFileParser.parse();

        assertEquals(26, result.size());
    }

    @Test(expected = Exception.class)
    public void parseNotFile() throws Exception {
        Map<Integer, String> result;
        BasicFileParser basicFileParser = new BasicFileParser("src/input.cpp");
        basicFileParser.parse();
    }
}