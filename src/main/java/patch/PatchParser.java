package patch;

import java.io.IOException;

public interface PatchParser {
    Patch parsePatch() throws IOException;
}
