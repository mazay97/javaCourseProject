package file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicFileParser implements FileParser {
    private String fileName;

    public BasicFileParser(String name) {
        fileName = name;
    }

    public Map<Integer, String> parse() throws IOException {
        Map<Integer, String> result = new HashMap<>();
        List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);

        Integer stringNumber = 0;
        for (String line : lines) {
            result.put(stringNumber, line);
            ++stringNumber;
        }

        return result;
    }
}
