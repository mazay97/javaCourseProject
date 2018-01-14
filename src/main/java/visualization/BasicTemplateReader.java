package visualization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasicTemplateReader implements TemplateReader {
    private List<String> template = new ArrayList<>();

    public void readTemplate(final String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));

        String str;
        while ((str = br.readLine()) != null) {
            template.add(str + '\n');
        }

    }

    public List<String> getTemplate() {
        return template;
    }
}
