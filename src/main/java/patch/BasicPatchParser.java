package patch;

import etc.BlockData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BasicPatchParser implements PatchParser {
    private final char NUMBER_STRING_LITERAL = '@';
    private int FILE_BLOCK = 0;
    private final Integer LENGTH_OF_NUMBER_STRING = 4;
    private final char DELETED_STRING_LITERAL = '-';
    private final char ADDED_STRING_LITERAL = '+';
    private static final String FILE_BLOCK_BEGINNING = "---";
    private static final String BLOCK_INFO_BEGINNING = "@@";
    private static final String USER_STRING_NAME = "From:";
    private static final String DATE_STRING_NAME = "Date:";

    private String mFileToParse;
    private List<PatchString> mStrings = new ArrayList<>();

    public BasicPatchParser(String file){
        mFileToParse = file;
    }

    public BasicPatch parsePatch() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(mFileToParse), StandardCharsets.UTF_8);

        String name = checkInfo(lines.get(1), USER_STRING_NAME);
        String date = checkInfo(lines.get(2), DATE_STRING_NAME);


        findFileBlock(lines);

        String fileName = lines.get(FILE_BLOCK + 1);

        if (FILE_BLOCK == 0){
            throw new IOException("Can't find file block");
        }

        readLines(lines);

        return new BasicPatch(name, date, fileName, mStrings);
    }

    private String checkInfo(String info, String expected) throws IOException{
        String[] strings = info.split("\\s");
        if (!strings[0].equals(expected)){
            throw new IOException("Unexpected name of string");
        }

        strings[0] = "";

        return String.join(" ", strings);
    }

    private void findFileBlock(List<String> lines){
        for (int i = 0; i < lines.size(); i++){
            if (lines.get(i).equals(FILE_BLOCK_BEGINNING)){
                FILE_BLOCK = i;
                break;
            }
        }
    }

    private void readLines(List<String> lines){
        BlockData stringNumber;
        for (int i = FILE_BLOCK; i < lines.size(); i++){
            if (lines.get(i).length() > 0){
                if (lines.get(i).charAt(0) == NUMBER_STRING_LITERAL){
                    stringNumber = parseStringNumber(lines.get(i));
                    i = parseStrings(lines, i, stringNumber);
                }
            }
        }
    }

    private BlockData parseStringNumber(String line) throws NumberFormatException{
        BlockData result;
        String[] splittedLine = line.split("\\s");

        if (splittedLine.length >= LENGTH_OF_NUMBER_STRING){
            if (!splittedLine[0].equals(BLOCK_INFO_BEGINNING) || !splittedLine[3].equals(BLOCK_INFO_BEGINNING)){
                throw new IllegalArgumentException("Wrong format of number string");
            }
            String[] addedData = splittedLine[2].split(",");
            String[] deletedData = splittedLine[1].split(",");

            Integer addedNumber = Integer.parseInt(addedData[0].replaceAll("\\+", ""));
            Integer deletedNumber = Integer.parseInt(deletedData[0].replaceAll("-", ""));

            result = new BlockData(addedNumber, deletedNumber);
        } else {
            throw new IllegalArgumentException("Wrong format of number string");
        }

        return result;
    }

    private int parseStrings(List<String> lines, int ind, BlockData data){
        int resultInd = lines.size();
        Integer addedStringNumber = data.getAddedStringNumber();
        Integer deletedStringNumber = data.getDeletedStringNumber();
        Integer index = data.getAddedStringNumber();
        BasicPatchString string;

        boolean wasFound;
        for (int i = ind + 1; i < lines.size(); i++) {
            resultInd = i;

            if (lines.get(i).length() > 0) {
                if (lines.get(i).charAt(0) == NUMBER_STRING_LITERAL) {
                    return resultInd - 1;
                }

                if (lines.get(i).charAt(0) == ADDED_STRING_LITERAL){
                    string = new BasicPatchString(addedStringNumber, lines.get(i).charAt(0), lines.get(i).substring(1, lines.get(i).length()));
                    string.setAddedStringNumber(addedStringNumber);
                    mStrings.add(string);
                    addedStringNumber++;
                    index++;
                    wasFound = true;
                } else if (lines.get(i).charAt(0) == DELETED_STRING_LITERAL){
                    mStrings.add(new BasicPatchString(deletedStringNumber, lines.get(i).charAt(0), lines.get(i).substring(1, lines.get(i).length())));
                    deletedStringNumber++;
                    wasFound = true;
                } else {
                    wasFound = false;
                    index++;
                }
            } else {
                wasFound = false;
                index++;
            }
            if (!wasFound){
                string = new BasicPatchString(index - 1, ' ', lines.get(i));
                string.setAddedStringNumber(addedStringNumber);
                mStrings.add(new BasicPatchString(string));
                addedStringNumber++;
                deletedStringNumber++;
            }

        }
        return resultInd + 1;
    }
}