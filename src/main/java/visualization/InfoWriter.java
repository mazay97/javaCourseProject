package visualization;

import patch.Patch;

import java.util.ArrayList;

public class InfoWriter {
    private final String OPENED_H = "<h2>";
    private final String CLOSED_H = "</h2>";
    private String author;
    private String date;
    private String file;
    private Integer infoBlockNumber;


    public InfoWriter(Patch patch, Integer blockNumber){
        author = patch.getName();
        date = patch.getDateOfChange();
        file = patch.getFileName();
        infoBlockNumber = blockNumber;
    }

    public void wrap(ArrayList<String> template){

        if (infoBlockNumber > template.size()){
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
