package visualization;

import org.junit.Test;
import patch.BasicPatchString;
import patch.PatchString;
import pojo.MergedData;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BasicUnifiedWriterTest {
    @Test
    public void unifiedEmptyTemplate() throws Exception {
        MergedData data = new MergedData(new ArrayList<>(), new ArrayList<>());
        List<String> template = new ArrayList<>();

        DataWriter unifiedWriter = new BasicUnifiedWriter(data, template);
        unifiedWriter.generateHtml("test3.html");

        List<String> lines = Files.readAllLines(Paths.get("test3.html"), StandardCharsets.UTF_8);
        assertEquals(0, lines.size());
    }

    @Test
    public void unifiedNormalTemplateWithEmptyData() throws Exception {
        MergedData data = new MergedData(new ArrayList<>(), new ArrayList<>());
        BasicTemplateReader basicTemplateReader = new BasicTemplateReader();
        basicTemplateReader.readTemplate("unified.html");
        List<String> template = basicTemplateReader.getTemplate();

        BasicUnifiedWriter unifiedWriter = new BasicUnifiedWriter(data, template);
        unifiedWriter.generateHtml("test4.html");

        List<String> lines = Files.readAllLines(Paths.get("test4.html"), StandardCharsets.UTF_8);
        assertEquals(25, lines.size());
    }

    @Test
    public void unifiedNormalTemplateWithNormalData() throws Exception {
        List<PatchString> added = new ArrayList<>();
        List<PatchString> deleted = new ArrayList<>();
        added.add(new BasicPatchString(1, ' ', "asd"));
        added.add(new BasicPatchString(2, ' ', "asd1"));
        added.add(new BasicPatchString(3, '+', "asd1"));
        deleted.add(new BasicPatchString(1, '-', "asd123"));
        deleted.add(new BasicPatchString(2, ' ', "asd1"));
        deleted.add(new BasicPatchString(3, '-', "asd123"));
        MergedData data = new MergedData(added, deleted);
        BasicTemplateReader basicTemplateReader = new BasicTemplateReader();
        basicTemplateReader.readTemplate("unified.html");
        List<String> template = basicTemplateReader.getTemplate();

        DataWriter unifiedWriter = new BasicUnifiedWriter(data, template);
        unifiedWriter.generateHtml("test4.html");

        List<String> lines = Files.readAllLines(Paths.get("test4.html"), StandardCharsets.UTF_8);
        assertEquals(40, lines.size());
    }
}