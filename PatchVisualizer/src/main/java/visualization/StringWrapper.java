package visualization;

import patch.PatchString;

public interface StringWrapper {
    String wrap(PatchString toWrap);

    String wrap(PatchString toWrap, Integer num);
}
