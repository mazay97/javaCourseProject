package patch;

import java.util.ArrayList;
import java.util.List;

public class BasicPatch implements Patch {
    private String mName = "";
    private String mDateOfChange = "";
    private String mFileName = "";
    private List<PatchString> mString = new ArrayList<>();

    public BasicPatch(){}

    public BasicPatch(String name, String date, String fileName, List<PatchString> strings){
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

    public List<PatchString> getStrings() {
        return mString;
    }

}
