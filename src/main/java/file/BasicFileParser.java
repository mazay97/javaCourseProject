package file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicFileParser implements FileParser {
    private String mFileName = "";

    public BasicFileParser(String name) {
        mFileName = name;
    }

    public Map<Integer, String> parse() throws IOException {
        Map<Integer, String> result = new HashMap<>();
        Integer stringNumber = 0;

        List<String> lines = Files.readAllLines(Paths.get(mFileName), StandardCharsets.UTF_8);
        for (String line : lines) {
            result.put(stringNumber, line);
            ++stringNumber;
        }

        return result;
    }
}
