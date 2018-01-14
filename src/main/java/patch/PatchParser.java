package patch;

import etc.BlockData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PatchParser {
    private final char ADDED_STRING_LITERAL = '+';
    private final char DELETED_STRING_LITERAL = '-';
    private final char NUMBER_STRING_LITERAL = '@';
    private final String USER_STRING_NAME = "From:";
    private final String DATE_STRING_NAME = "Date:";
    private final Integer LENGTH_OF_NUMBER_STRING = 4;
    private int FILE_BLOCK = 0;

    private String mFileToParse;
    private ArrayList<PatchString> strings = new ArrayList<>();

    public PatchParser(String file){
        mFileToParse = file;
    }

    public Patch parsePatch() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(mFileToParse), StandardCharsets.UTF_8);

        String name = checkInfo(lines.get(1), USER_STRING_NAME);
        String date = checkInfo(lines.get(2), DATE_STRING_NAME);


        findFileBlock(lines);

        String fileName = lines.get(FILE_BLOCK + 1);

        if (FILE_BLOCK == 0){
            throw new IOException("Can't find file block");
        }

        readLines(lines);

        return new Patch(name, date, fileName, strings);
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
            if (lines.get(i).equals("---")){
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
            if (!splittedLine[0].equals("@@") || !splittedLine[3].equals("@@")){
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

        boolean wasFound = false;
        for (int i = ind + 1; i < lines.size(); i++) {
            resultInd = i;

            if (lines.get(i).length() > 0) {
                if (lines.get(i).charAt(0) == NUMBER_STRING_LITERAL) {
                    return resultInd - 1;
                }
                if (lines.get(i).charAt(0) == ADDED_STRING_LITERAL){
                    strings.add(new PatchString(addedStringNumber, lines.get(i).charAt(0), lines.get(i).substring(1, lines.get(i).length())));
                    addedStringNumber++;
                    index++;
                    wasFound = true;
                } else if (lines.get(i).charAt(0) == DELETED_STRING_LITERAL){
                    strings.add(new PatchString(deletedStringNumber, lines.get(i).charAt(0), lines.get(i).substring(1, lines.get(i).length())));
                    deletedStringNumber++;
                    wasFound = true;
                } else {
                    wasFound = false;
                    index++;
                }
            } else {
                index++;
            }
            if (!wasFound){
                strings.add(new PatchString(index - 1, ' ', lines.get(i)));
                addedStringNumber++;
                deletedStringNumber++;
                wasFound = false;
            }

        }
        return resultInd + 1;
    }
}
