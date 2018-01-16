package visualization;

import patch.PatchString;

interface StringWrapper {
    String wrap(PatchString toWrap);

    String wrap(PatchString toWrap, Integer num);
}
