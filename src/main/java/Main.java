import java.io.IOException;

public class Main {

    public static void main(String[] args){
        try {
            BasicPatchVisualizer basicPatchVisualizer = new BasicPatchVisualizer(args);
            basicPatchVisualizer.run();
        } catch (IllegalArgumentException | IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}
