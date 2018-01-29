<<<<<<< HEAD
package pojo;

import patch.PatchString;

import java.util.List;

public class MergedData {
    private final List<PatchString> mAdded;
    private final List<PatchString> mDeleted;

    public MergedData(List<PatchString> added, List<PatchString> deleted) {
        mAdded = added;
        mDeleted = deleted;
    }

    public List<PatchString> getAdded() {
        return mAdded;
    }

    public List<PatchString> getDeleted() {
        return mDeleted;
    }
}
=======
package pojo;

import patch.PatchString;

import java.util.List;

public class MergedData {
    private final List<PatchString> mAdded;
    private final List<PatchString> mDeleted;

    public MergedData(List<PatchString> added, List<PatchString> deleted) {
        mAdded = added;
        mDeleted = deleted;
    }

    public List<PatchString> getAdded() {
        return mAdded;
    }

    public List<PatchString> getDeleted() {
        return mDeleted;
    }
}
>>>>>>> origin/master
