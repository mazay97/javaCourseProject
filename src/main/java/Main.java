import file.BasicFileParser;
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
    private static final String UNIFIED_TEMPLATE = "unified.html";
    private static final String SPLIT_TEMPLATE = "split.html";

    private static final Integer SPLIT_INFO_BLOCK_NUMBER = 28;
    private static final Integer UNIFIED_INFO_BLOCK_NUMBER = 20;

    private static String patchFile = "";
    private static String sourceFile = "";
    private static String outFile = "";
    private static String mode = "";

    private static Patch basicPatch = new BasicPatch();
    private static Map<Integer, String> file = new HashMap<>();

    public static void main(String[] args){
        try {
            readArguments(args);
            parsePatch();
            parseFile();
            generateHtmlData();
        } catch (NumberFormatException ex){
            System.out.println("Wrong data in basicPatch in file block");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    private static void readArguments(final String[] args){
        BasicArgumentParser basicArgumentParser = new BasicArgumentParser();
        basicArgumentParser.addFlag(FILE_FLAG);
        basicArgumentParser.addFlag(PATCH_FLAG);
        basicArgumentParser.addFlag(OUT_FLAG);
        basicArgumentParser.addFlag(MODE_FLAG);

        basicArgumentParser.parse(args);

        patchFile = basicArgumentParser.getValue(PATCH_FLAG);
        sourceFile = basicArgumentParser.getValue(FILE_FLAG);
        outFile = basicArgumentParser.getValue(OUT_FLAG);
        mode = basicArgumentParser.getValue(MODE_FLAG);
    }

    private static void parsePatch() throws IOException{
        PatchParser pp = new BasicPatchParser(patchFile);
        basicPatch = pp.parsePatch();
    }

    private static void parseFile() throws IOException{
        BasicFileParser fp = new BasicFileParser(sourceFile);
        file = fp.parse();
    }

    private static void generateHtmlData() throws IOException{
        TemplateReader tr = new BasicTemplateReader();
        DataMerger dm = new BasicDataMerger(basicPatch.getStrings(), file);
        InfoWriter infoWriter;

        List<String> htmlTemplate;
        switch (mode) {
            case SPLIT_MODE: {
                tr.readTemplate(SPLIT_TEMPLATE);
                htmlTemplate = tr.getTemplate();
                infoWriter = new BasicInfoWriter(basicPatch, SPLIT_INFO_BLOCK_NUMBER);
                infoWriter.wrap(htmlTemplate);
                BasicSplitWriter generator = new BasicSplitWriter(dm.getMergedData(), htmlTemplate);
                generator.generateHtml(outFile);
                break;
            }
            case UNIFIED_MODE: {
                tr.readTemplate(UNIFIED_TEMPLATE);
                htmlTemplate = tr.getTemplate();
                infoWriter = new BasicInfoWriter(basicPatch, UNIFIED_INFO_BLOCK_NUMBER);
                infoWriter.wrap(htmlTemplate);
                BasicUnifiedWriter generator = new BasicUnifiedWriter(dm.getMergedData(), htmlTemplate);
                generator.generateHtml(outFile);
                break;
            }
            default:
                throw new IllegalArgumentException("Wrong name of visualization mode");
        }

    }
}
