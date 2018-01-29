<<<<<<< HEAD
import java.io.IOException;
import java.nio.file.NoSuchFileException;
=======
import file.BasicFileParser;
import patch.BasicPatch;
import patch.BasicPatchParser;
import patch.Patch;
import patch.PatchParser;
import visualization.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
>>>>>>> origin/master

public class Main {

    public static void main(String[] args){
        try {
            BasicPatchVisualizer basicPatchVisualizer = new BasicPatchVisualizer(args);
            basicPatchVisualizer.run();
        } catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        } catch (NoSuchFileException ex){
            System.out.println(ex.getMessage());
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}
