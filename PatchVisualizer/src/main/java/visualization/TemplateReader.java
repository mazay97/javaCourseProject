package visualization;

import java.io.IOException;
import java.util.List;

public interface TemplateReader {
    void readTemplate(String path) throws IOException;
    List<String> getTemplate();
}
