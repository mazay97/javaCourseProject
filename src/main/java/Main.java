import com.sun.javaws.exceptions.InvalidArgumentException;
import file.BasicFileParser;
import file.FileParser;
import patch.BasicPatch;
import patch.BasicPatchParser;
import patch.Patch;
import patch.PatchParser;
import visualization.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String FILE_FLAG = "--file";
    private static final String PATCH_FLAG = "--patch";
    private static final String OUT_FLAG = "--out";
    private static final String MODE_FLAG = "--mode";
    private static final String SPLIT_MODE = "split";
    private static final String UNIFIED_MODE = "unified";
    private static final String UNIFIED_TEMPLATE = "src/input/unified.html";
    private static final String SPLIT_TEMPLATE = "src/input/split.html";

    private static final Integer SPLIT_INFO_BLOCK_NUMBER = 28;
    private static final Integer UNIFIED_INFO_BLOCK_NUMBER = 20;

    private static String patchFile;
    private static String sourceFile;
    private static String outFile;
    private static String mode;

    private static Patch patch = new BasicPatch();
    private static Map<Integer, String> file = new HashMap<>();

    public static void main(String[] args) {
        try {
            readArguments(args);
            parsePatch();
            parseFile();
            generateHtmlData();
        } catch (IllegalArgumentException e) {
            System.out.println("Use: java patchVisualizer --file <file> --patch <patch> --out <out> --mode <mode>");
        } catch (IOException e) {
            System.out.println("IO failed, check file names");
        }
    }

    private static void readArguments(final String[] args) {
        ArgumentParser argumentParser = new BasicArgumentParser();
        argumentParser.addFlag(FILE_FLAG);
        argumentParser.addFlag(PATCH_FLAG);
        argumentParser.addFlag(OUT_FLAG);
        argumentParser.addFlag(MODE_FLAG);

        argumentParser.parse(args);

        patchFile = argumentParser.getValue(PATCH_FLAG);
        sourceFile = argumentParser.getValue(FILE_FLAG);
        outFile = argumentParser.getValue(OUT_FLAG);
        mode = argumentParser.getValue(MODE_FLAG);
    }

    private static void parsePatch() throws IOException {
        PatchParser pp = new BasicPatchParser(patchFile);
        patch = pp.parsePatch();
    }

    private static void parseFile() throws IOException {
        FileParser fp = new BasicFileParser(sourceFile);
        file = fp.parse();
    }

    private static void generateHtmlData() throws IOException {
        TemplateReader tr = new BasicTemplateReader();
        DataMerger dm = new BasicDataMerger(patch.getStrings(), file);
        InfoWriter infoWriter;
        List<String> htmlTemplate;

        if (mode.equals(SPLIT_MODE)) {
            tr.readTemplate(SPLIT_TEMPLATE);
            htmlTemplate = tr.getTemplate();
            infoWriter = new BasicInfoWriter(patch, SPLIT_INFO_BLOCK_NUMBER);
            infoWriter.wrap(htmlTemplate);
            DataWriter generator = new BasicSplitWriter(dm.getMergedData(), htmlTemplate);
            generator.generateHtml(outFile);
        } else if (mode.equals(UNIFIED_MODE)) {
            tr.readTemplate(UNIFIED_TEMPLATE);
            htmlTemplate = tr.getTemplate();
            infoWriter = new BasicInfoWriter(patch, UNIFIED_INFO_BLOCK_NUMBER);
            infoWriter.wrap(htmlTemplate);
            DataWriter generator = new BasicUnifiedWriter(dm.getMergedData(), htmlTemplate);
            generator.generateHtml(outFile);
        } else {
            throw new IllegalArgumentException("Wrong name of visualization mode");
        }
    }
}
