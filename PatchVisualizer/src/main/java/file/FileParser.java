package file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class FileParser {
    private String fileName = new String();

    public FileParser(String name){
        fileName = name;
    }

    public HashMap<Integer, String> parse() throws IOException{
        HashMap<Integer, String> result = new HashMap<>();
        Integer stringNumber = new Integer(0);

        List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        for (String line: lines){
            result.put(stringNumber, line);
            ++stringNumber;
        }

        return result;
    }
}
