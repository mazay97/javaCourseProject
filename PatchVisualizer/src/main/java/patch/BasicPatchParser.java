package patch;

import pojo.BlockData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BasicPatchParser implements PatchParser {
    private static final char ADDED_STRING_LITERAL = '+';
    private static final char DELETED_STRING_LITERAL = '-';
    private static final char NUMBER_STRING_LITERAL = '@';
    private static final String USER_STRING_NAME = "From:";
    private static final String DATE_STRING_NAME = "Date:";
    private static final String FILE_BLOCK_BEGINNING = "---";
    private static final String BLOCK_INFO_BEGINNING = "@@";
    private static final Integer LENGTH_OF_NUMBER_STRING = 4;

    private int mFileBlock = 0;
    private String mFileToParse;
    private List<PatchString> mStrings = new ArrayList<>();

    public BasicPatchParser(String file) {
        mFileToParse = file;
    }

    public Patch parsePatch() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(mFileToParse), StandardCharsets.UTF_8);

        String name = checkInfo(lines.get(1), USER_STRING_NAME);
        String date = checkInfo(lines.get(2), DATE_STRING_NAME);

        findFileBlock(lines);

        String fileName = lines.get(mFileBlock + 1);

        if (mFileBlock == 0) {
            throw new IOException("Can't find file block");
        }

        readLines(lines);

        return new BasicPatch(name, date, fileName, mStrings);
    }

    private String checkInfo(String info, String expected) throws IOException {
        String[] strings = info.split("\\s");
        if (!strings[0].equals(expected)) {
            throw new IOException("Unexpected name of string");
        }

        strings[0] = "";

        return String.join(" ", strings);
    }

    private void findFileBlock(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals(FILE_BLOCK_BEGINNING)) {
                mFileBlock = i;
                break;
            }
        }
    }

    private void readLines(List<String> lines) {
        BlockData stringNumber;
        for (int i = mFileBlock; i < lines.size(); i++) {
            if (lines.get(i).length() > 0) {
                if (lines.get(i).charAt(0) == NUMBER_STRING_LITERAL) {
                    stringNumber = parseBlockInfo(lines.get(i));
                    i = parseStrings(lines, i, stringNumber);
                }
            }
        }
    }

    private BlockData parseBlockInfo(String line) throws IllegalArgumentException {
        BlockData result;
        String[] splittedLine = line.split("\\s");

        if (!splittedLine[0].equals(BLOCK_INFO_BEGINNING) && !splittedLine[3].equals(BLOCK_INFO_BEGINNING)) {
            throw new IllegalArgumentException("Wrong format of block info");
        }

        if (splittedLine.length >= LENGTH_OF_NUMBER_STRING) {
            String[] addedData = splittedLine[2].split(",");
            String[] deletedData = splittedLine[1].split(",");

            Integer addedNumber = Integer.parseInt(addedData[0]);
            Integer deletedNumber = Integer.parseInt(deletedData[0]);

            result = new BlockData(addedNumber, deletedNumber);
        } else {
            throw new IllegalArgumentException("Wrong format of block info");
        }

        return result;
    }

    private int parseStrings(List<String> lines, int startIndex, BlockData data) {
        int resultIndex = lines.size();
        Integer addedStringNumber = data.getAddedStringNumber();
        Integer deletedStringNumber = data.getDeletedStringNumber();
        Integer index = data.getAddedStringNumber();

        boolean wasFound = false;
        for (int i = startIndex + 1; i < lines.size(); i++) {
            resultIndex = i;

            if (lines.get(i).length() > 0) {
                if (lines.get(i).charAt(0) == NUMBER_STRING_LITERAL) {
                    return resultIndex - 1;
                }
                if (lines.get(i).charAt(0) == ADDED_STRING_LITERAL) {
                    mStrings.add(new BasicPatchString(addedStringNumber, lines.get(i).charAt(0), lines.get(i).substring(1, lines.get(i).length())));
                    addedStringNumber++;
                    index++;
                    wasFound = true;
                } else if (lines.get(i).charAt(0) == DELETED_STRING_LITERAL) {
                    mStrings.add(new BasicPatchString(deletedStringNumber, lines.get(i).charAt(0), lines.get(i).substring(1, lines.get(i).length())));
                    deletedStringNumber++;
                    wasFound = true;
                } else {
                    wasFound = false;
                    index++;
                }
            } else {
                index++;
            }
            if (!wasFound) {
                mStrings.add(new BasicPatchString(index - 1, ' ', lines.get(i)));
                addedStringNumber++;
                deletedStringNumber++;
                wasFound = false;
            }

        }
        return resultIndex + 1;
    }
}
