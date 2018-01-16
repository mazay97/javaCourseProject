package file;

import java.io.IOException;
import java.util.Map;

interface FileParser {
    Map<Integer, String> parse() throws IOException;
}
