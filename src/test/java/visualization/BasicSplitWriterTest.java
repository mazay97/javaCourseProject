package visualization;

import org.junit.Test;
import patch.BasicPatchString;
import patch.PatchString;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BasicSplitWriterTest {
    @Test
    public void splitEmptyTemplate() throws Exception {
        List<List<PatchString>> data = new ArrayList<>();
        data.add(new ArrayList<>());
        data.add(new ArrayList<>());
        ArrayList<String> template = new ArrayList<>();

        BasicSplitWriter splitWriter = new BasicSplitWriter(data, template);
        splitWriter.generateHtml("test.html");

        List<String> lines = Files.readAllLines(Paths.get("test.html"), StandardCharsets.UTF_8);
        assertEquals(0, lines.size());
    }

    @Test
    public void splitNormalTemplateWithEmptyData() throws Exception {
        List<List<PatchString>> data = new ArrayList<>();
        data.add(new ArrayList<>());
        data.add(new ArrayList<>());
        BasicTemplateReader basicTemplateReader = new BasicTemplateReader();
        basicTemplateReader.readTemplate("src/input/split.html");
        List<String> template = basicTemplateReader.getTemplate();

        BasicSplitWriter splitWriter = new BasicSplitWriter(data, template);
        splitWriter.generateHtml("test1.html");

        List<String> lines = Files.readAllLines(Paths.get("test1.html"), StandardCharsets.UTF_8);
        assertEquals(36, lines.size());
    }

    @Test
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
        basicTemplateReader.readTemplate("src/input/split.html");
        List<String> template = basicTemplateReader.getTemplate();

        BasicSplitWriter splitWriter = new BasicSplitWriter(data, template);
        splitWriter.generateHtml("test4.html");

        List<String> lines = Files.readAllLines(Paths.get("test4.html"), StandardCharsets.UTF_8);
        assertEquals(60, lines.size());
    }
}