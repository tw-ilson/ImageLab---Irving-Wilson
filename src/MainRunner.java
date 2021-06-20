import controller.ImageProcessorController;
import controller.SimpleImageController;
import java.io.FileNotFoundException;
import java.io.FileReader;
import model.ImageProcessorLayerModel;
import model.LayeredImageModel;

public class MainRunner {
  public static void main(String[] args) {
    ImageProcessorLayerModel model = new LayeredImageModel();
    ImageProcessorController controller = new SimpleImageController(model, System.out);
    try {
      controller.run(new FileReader("example-run.txt"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
