package visualization;

import patch.Patch;

import java.util.List;

public class BasicInfoWriter implements InfoWriter {
    private static final String OPENED_H = "<h2>";
    private static final String CLOSED_H = "</h2>";
    private String author;
    private String date;
    private String file;
    private Integer infoBlockNumber;


    public BasicInfoWriter(Patch patch, Integer blockNumber) {
        author = patch.getName();
        date = patch.getDateOfChange();
        file = patch.getFileName();
        infoBlockNumber = blockNumber;
    }

    public void wrap(List<String> template) {
        if (infoBlockNumber > template.size()) {
            throw new IllegalArgumentException("Info block line number is higher than file length");
        }
        String temp = OPENED_H + "Author: " + author + CLOSED_H;
        template.add(infoBlockNumber, temp);
        temp = OPENED_H + "Date: " + date + CLOSED_H;
        template.add(infoBlockNumber, temp);
        temp = OPENED_H + "File: " + file + CLOSED_H;
        template.add(infoBlockNumber, temp);
    }
}
