import patch.Patch;
import patch.PatchString;

import java.util.List;
import java.util.Map;

public class BasicFileAndPatchComparator implements Comparator {
    private final Patch mPatch;
    private final Map<Integer, String> mFile;
    private final String mFileName;

    public BasicFileAndPatchComparator(Patch patch, Map<Integer, String> file, String fileName) {
        mPatch = patch;
        mFile = file;
        mFileName = fileName;
    }

    public void compare() throws IllegalArgumentException {
        compareFileName();
        comparePatchAndFile();
    }

    private void compareFileName() throws IllegalArgumentException {
        String[] file = mFileName.split("/");
        if (!mPatch.getFileName().contains(file[file.length - 1])){
            throw new IllegalArgumentException("File in patch is not same then file in args");
        }
    }

    private void comparePatchAndFile() throws IllegalArgumentException {
        List<PatchString> patchStrings = mPatch.getStrings();
        for (PatchString patchString : patchStrings) {
            if (patchString.getStatus() == '-') {
                if (!patchString.getValue().equals(mFile.get(patchString.getDeletedStringNumber() - 1))) {
                    System.out.println(patchString.getValue() + " " + (mFile.get(patchString.getDeletedStringNumber() - 1)));
                    throw new IllegalArgumentException("Deleted string in file and patch not the same");
                }
            }
        }
    }
}
