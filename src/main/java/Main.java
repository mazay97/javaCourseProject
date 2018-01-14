import file.FileParser;
import patch.Patch;
import patch.PatchParser;
import visualization.*;
import visualization.DataMerger;
import visualization.SplitWriter;
import visualization.UnifiedWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

    private static String patchFile = new String();
    private static String sourceFile = new String();
    private static String outFile = new String();
    private static String mode = new String();

    private static Patch patch = new Patch();
    private static HashMap<Integer, String> file = new HashMap<>();
    private static ArrayList<String> htmlTemplate = new ArrayList<>();

    public static void main(String[] args){
        try {
            readArguments(args);
            parsePatch();
            parseFile();
            generateHtmlData();
        } catch (NumberFormatException ex){
            System.out.println("Wrong data in patch in file block");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }

    private static void readArguments(final String[] args){
        ArgumentParser argumentParser = new ArgumentParser();
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

    private static void parsePatch() throws IOException{
        PatchParser pp = new PatchParser(patchFile);
        patch = pp.parsePatch();
    }

    private static void parseFile() throws IOException{
        FileParser fp = new FileParser(sourceFile);
        file = fp.parse();
    }

    private static void generateHtmlData() throws IOException{
        TemplateReader tr = new TemplateReader();
        DataMerger dm = new DataMerger(patch.getStrings(), file);
        InfoWriter infoWriter;

        if (mode.equals(SPLIT_MODE)){
            tr.readTemplate(SPLIT_TEMPLATE);
            htmlTemplate = tr.getTemplate();
            infoWriter = new InfoWriter(patch, SPLIT_INFO_BLOCK_NUMBER);
            infoWriter.wrap(htmlTemplate);
            SplitWriter generator = new SplitWriter(dm.getMergedData(), htmlTemplate);
            generator.genHtml(outFile);
        } else if (mode.equals(UNIFIED_MODE)){
            tr.readTemplate(UNIFIED_TEMPLATE);
            htmlTemplate = tr.getTemplate();
            infoWriter = new InfoWriter(patch, UNIFIED_INFO_BLOCK_NUMBER);
            infoWriter.wrap(htmlTemplate);
            UnifiedWriter generator = new UnifiedWriter(dm.getMergedData(), htmlTemplate);
            generator.genHtml(outFile);
        } else {
            throw new IllegalArgumentException("Wrong name of visualization mode");
        }

    }
}
