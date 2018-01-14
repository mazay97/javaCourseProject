package visualization;

import org.junit.Test;
import patch.PatchString;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UnifiedWriterTest {
    @Test
    public void unifiedEmptyTemplate() throws Exception {
        ArrayList<ArrayList<PatchString>> data = new ArrayList<>();
        data.add(new ArrayList<>());
        data.add(new ArrayList<>());
        ArrayList<String> template = new ArrayList<>();

        UnifiedWriter unifiedWriter = new UnifiedWriter(data, template);
        unifiedWriter.genHtml("test3.html");

        List<String> lines = Files.readAllLines(Paths.get("test3.html"), StandardCharsets.UTF_8);
        assertEquals(0, lines.size());
    }

    @Test
    public void unifiedNormalTemplateWithEmptyData() throws Exception {
        ArrayList<ArrayList<PatchString>> data = new ArrayList<>();
        data.add(new ArrayList<>());
        data.add(new ArrayList<>());
        TemplateReader templateReader = new TemplateReader();
        templateReader.readTemplate("src/input/unified.html");
        ArrayList<String> template = templateReader.getTemplate();

        UnifiedWriter unifiedWriter = new UnifiedWriter(data, template);
        unifiedWriter.genHtml("test4.html");

        List<String> lines = Files.readAllLines(Paths.get("test4.html"), StandardCharsets.UTF_8);
        assertEquals(25, lines.size());
    }

    @Test
    public void unifiedNormalTemplateWithNormalData() throws Exception {
        ArrayList<ArrayList<PatchString>> data = new ArrayList<>();
        ArrayList<PatchString> added = new ArrayList<>();
        ArrayList<PatchString> deleted = new ArrayList<>();
        added.add(new PatchString(1, '+', "asd"));
        added.add(new PatchString(2, ' ', "asd1"));
        added.add(new PatchString(3, ' ', "asd1"));
        deleted.add(new PatchString(1, '-', "asd123"));
        deleted.add(new PatchString(2, ' ', "asd1"));
        deleted.add(new PatchString(3, '-', "asd123"));
        data.add(deleted);
        data.add(added);
        TemplateReader templateReader = new TemplateReader();
        templateReader.readTemplate("src/input/unified.html");
        ArrayList<String> template = templateReader.getTemplate();

        UnifiedWriter unifiedWriter = new UnifiedWriter(data, template);
        unifiedWriter.genHtml("test4.html");

        List<String> lines = Files.readAllLines(Paths.get("test4.html"), StandardCharsets.UTF_8);
        assertEquals(45, lines.size());
    }
}