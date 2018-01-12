package patch;

import java.util.ArrayList;

public class Patch {
    private String mName;
    private String mDateOfChange;
    private String mFileName;
    private ArrayList<PatchString> mString = new ArrayList<PatchString>();

    public Patch(){}

    public Patch(String name, String date, String fileName, ArrayList<PatchString> strings){
        mName = name;
        mDateOfChange = date;
        mFileName = fileName;
        mString = strings;
    }

    public String getName() {
        return mName;
    }

    public String getDateOfChange() {
        return mDateOfChange;
    }

    public String getFileName() {
        return mFileName;
    }

    public void setStrings(ArrayList<PatchString> mergedStrings) {
        mString = mergedStrings;
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
