import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicArgumentParserTest {

    @Test
    public void parse() {
        String[] args = {"--file", "src/input/rules.js", "--patch", "src/input/rules.patch", "--out", "changes.html", "--mode", "unified"};
        ArgumentParser argumentParser = new BasicArgumentParser();
        argumentParser.addFlag("--file");
        argumentParser.addFlag("--patch");
        argumentParser.addFlag("--out");
        argumentParser.addFlag("--mode");
        argumentParser.parse(args);
        assertEquals("src/input/rules.js", argumentParser.getValue("--file"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseWithZeroSizeOfArgs() {
        String[] args = {};
        ArgumentParser argumentParser = new BasicArgumentParser();
        argumentParser.addFlag("--file");
        argumentParser.addFlag("--patch");
        argumentParser.parse(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void flagExpected() {
        String[] args = {"blaBlaBla", "src/input/rules.js", "--patch"};
        ArgumentParser argumentParser = new BasicArgumentParser();
        argumentParser.addFlag("--file");
        argumentParser.addFlag("--patch");
        argumentParser.parse(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseWithDuplicateFlags() {
        String[] args = {"--file", "src/input/rules.js", "--patch", "src/input/rules.patch", "--file", "changes.html"};
        ArgumentParser argumentParser = new BasicArgumentParser();
        argumentParser.addFlag("--file");
        argumentParser.addFlag("--patch");
        argumentParser.parse(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addWrongFormatFlag() {
        ArgumentParser argumentParser = new BasicArgumentParser();
        argumentParser.addFlag("sa1231");
    }

}