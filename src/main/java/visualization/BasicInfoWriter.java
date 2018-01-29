package visualization;

import patch.Patch;

import java.util.List;

public class BasicInfoWriter implements InfoWriter {
    private final String mAuthor;
    private final String mDate;
    private final String mFile;
    private final Integer mInfoBlockNumber;
    private static final String OPENED_H = "<h2>";
    private static final String CLOSED_H = "</h2>";


    public BasicInfoWriter(Patch basicPatch, Integer blockNumber) {
        mAuthor = basicPatch.getName();
        mDate = basicPatch.getDateOfChange();
        mFile = basicPatch.getFileName();
        mInfoBlockNumber = blockNumber;
    }

    public void wrap(List<String> template) {

        if (mInfoBlockNumber > template.size()) {
            throw new IllegalArgumentException("Info block line number is higher than file length");
        }

        String temp = OPENED_H + "Author: " + mAuthor + CLOSED_H;
        template.add(mInfoBlockNumber, temp);
        temp = OPENED_H + "Date: " + mDate + CLOSED_H;
        template.add(mInfoBlockNumber, temp);
        temp = OPENED_H + "File: " + mFile + CLOSED_H;
        template.add(mInfoBlockNumber, temp);
    }
}
