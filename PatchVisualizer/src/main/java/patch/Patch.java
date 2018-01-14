package patch;

import java.util.ArrayList;

public class Patch {
    private String name = new String();
    private String dateOfChange = new String();
    private String fileName = new String();
    private ArrayList<PatchString> mString = new ArrayList<PatchString>();

    public Patch(){}

    public Patch(String name1, String date1, String fileName1, ArrayList<PatchString> strings1){
        name = name1;
        dateOfChange = date1;
        fileName = fileName1;
        mString = strings1;
    }

    public String getName() {
        return name;
    }

    public String getDateOfChange() {
        return dateOfChange;
    }

    public String getFileName() {
        return fileName;
    }

    public ArrayList<PatchString> getStrings() {
        return mString;
    }

    public void printPatch(){
        for (PatchString ptch: mString){
            System.out.println(ptch.getDeletedStringNumber() + " " + ptch.getStatus() + " " + ptch.getValue());
        }
    }
}
