package file;

import java.io.IOException;
import java.util.Map;

public interface FileParser {
    Map<Integer, String> parse() throws IOException;
}
