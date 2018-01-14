package visualization;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import patch.PatchString;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SplitWriterTest {
    @Test
    public void splitEmptyTemplate() throws Exception {
        ArrayList<ArrayList<PatchString>> data = new ArrayList<>();
        data.add(new ArrayList<>());
        data.add(new ArrayList<>());
        ArrayList<String> template = new ArrayList<>();

        SplitWriter splitWriter = new SplitWriter(data, template);
        splitWriter.genHtml("test.html");

        List<String> lines = Files.readAllLines(Paths.get("test.html"), StandardCharsets.UTF_8);
        assertEquals(0, lines.size());
    }

    @Test
    public void splitNormalTemplateWithEmptyData() throws Exception {
        ArrayList<ArrayList<PatchString>> data = new ArrayList<>();
        data.add(new ArrayList<>());
        data.add(new ArrayList<>());
        TemplateReader templateReader = new TemplateReader();
        templateReader.readTemplate("src/input/split.html");
        ArrayList<String> template = templateReader.getTemplate();

        SplitWriter splitWriter = new SplitWriter(data, template);
        splitWriter.genHtml("test1.html");

        List<String> lines = Files.readAllLines(Paths.get("test1.html"), StandardCharsets.UTF_8);
        assertEquals(36, lines.size());
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
        templateReader.readTemplate("src/input/split.html");
        ArrayList<String> template = templateReader.getTemplate();

        SplitWriter splitWriter = new SplitWriter(data, template);
        splitWriter.genHtml("test4.html");

        List<String> lines = Files.readAllLines(Paths.get("test4.html"), StandardCharsets.UTF_8);
        assertEquals(60, lines.size());
    }
}