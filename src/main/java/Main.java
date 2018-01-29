import java.io.IOException;
import java.nio.file.NoSuchFileException;

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
