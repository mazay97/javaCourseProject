package visualization;

import org.junit.Test;
import patch.BasicPatchString;
import patch.PatchString;

import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BasicUnifiedWriterTest {
    @Test
    public void unifiedEmptyTemplate() throws Exception {
        List<List<PatchString>> data = new ArrayList<>();
        data.add(new ArrayList<>());
        data.add(new ArrayList<>());
        List<String> template = new ArrayList<>();

        DataWriter unifiedWriter = new BasicUnifiedWriter(data, template);
        unifiedWriter.generateHtml("test3.html");

        List<String> lines = Files.readAllLines(Paths.get("test3.html"), StandardCharsets.UTF_8);
        assertEquals(0, lines.size());
    }

    @Test
    public void unifiedNormalTemplateWithEmptyData() throws Exception {
        List<List<PatchString>> data = new ArrayList<>();
        data.add(new ArrayList<>());
        data.add(new ArrayList<>());
        BasicTemplateReader basicTemplateReader = new BasicTemplateReader();
        basicTemplateReader.readTemplate("src/input/unified.html");
        List<String> template = basicTemplateReader.getTemplate();

        BasicUnifiedWriter unifiedWriter = new BasicUnifiedWriter(data, template);
        unifiedWriter.generateHtml("test4.html");

        List<String> lines = Files.readAllLines(Paths.get("test4.html"), StandardCharsets.UTF_8);
        assertEquals(25, lines.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void unifiedNormalTemplateWithNormalData() throws Exception {
        List<List<PatchString>> data = new ArrayList<>();
        List<PatchString> added = new ArrayList<>();
        List<PatchString> deleted = new ArrayList<>();
        added.add(new BasicPatchString(1, '+', "asd"));
        added.add(new BasicPatchString(2, ' ', "asd1"));
        added.add(new BasicPatchString(3, ' ', "asd1"));
        deleted.add(new BasicPatchString(1, '-', "asd123"));
        deleted.add(new BasicPatchString(2, ' ', "asd1"));
        deleted.add(new BasicPatchString(3, '-', "asd123"));
        data.add(deleted);
        data.add(added);
        BasicTemplateReader basicTemplateReader = new BasicTemplateReader();
        basicTemplateReader.readTemplate("src/input/unified.html");
        List<String> template = basicTemplateReader.getTemplate();

        BasicUnifiedWriter unifiedWriter = new BasicUnifiedWriter(data, template);
        unifiedWriter.generateHtml("test4.html");

        List<String> lines = Files.readAllLines(Paths.get("test4.html"), StandardCharsets.UTF_8);
        assertEquals(45, lines.size());
    }
}