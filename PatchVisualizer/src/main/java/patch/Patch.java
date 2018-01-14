package patch;

import java.util.List;

public interface Patch {
    String getName();

    String getDateOfChange();

    String getFileName();

    List<PatchString> getStrings();
}
