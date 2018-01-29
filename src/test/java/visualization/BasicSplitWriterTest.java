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

import static org.junit.Assert.*;

public class BasicSplitWriterTest {
    @Test
    public void splitEmptyTemplate() throws Exception {
        ArrayList<String> template = new ArrayList<>();
        MergedData data = new MergedData(new ArrayList<>(), new ArrayList<>());
        BasicSplitWriter splitWriter = new BasicSplitWriter(data, template);
        splitWriter.generateHtml("test.html");

        List<String> lines = Files.readAllLines(Paths.get("test.html"), StandardCharsets.UTF_8);
        assertEquals(0, lines.size());
    }

    @Test
    public void splitNormalTemplateWithEmptyData() throws Exception {
        MergedData data = new MergedData(new ArrayList<>(), new ArrayList<>());
        BasicTemplateReader basicTemplateReader = new BasicTemplateReader();
        basicTemplateReader.readTemplate("split.html");
        List<String> template = basicTemplateReader.getTemplate();

        BasicSplitWriter splitWriter = new BasicSplitWriter(data, template);
        splitWriter.generateHtml("test1.html");

        List<String> lines = Files.readAllLines(Paths.get("test1.html"), StandardCharsets.UTF_8);
        assertEquals(36, lines.size());
    }

    @Test
    public void unifiedNormalTemplateWithNormalData() throws Exception {
        List<PatchString> added = new ArrayList<>();
        List<PatchString> deleted = new ArrayList<>();
        added.add(new BasicPatchString(1, '+', "asd"));
        added.add(new BasicPatchString(2, ' ', "asd1"));
        added.add(new BasicPatchString(3, ' ', "asd1"));
        deleted.add(new BasicPatchString(1, '-', "asd123"));
        deleted.add(new BasicPatchString(2, ' ', "asd1"));
        deleted.add(new BasicPatchString(3, '-', "asd123"));
        MergedData data = new MergedData(added, deleted);

        TemplateReader basicTemplateReader = new BasicTemplateReader();
        basicTemplateReader.readTemplate("split.html");
        List<String> template = basicTemplateReader.getTemplate();

        DataWriter splitWriter = new BasicSplitWriter(data, template);
        splitWriter.generateHtml("test4.html");

        List<String> lines = Files.readAllLines(Paths.get("test4.html"), StandardCharsets.UTF_8);
        assertEquals(60, lines.size());
    }
}