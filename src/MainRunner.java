import controller.ImageProcessorController;
import controller.SimpleImageController;
import java.io.InputStreamReader;
import model.ImageProcessorLayerModel;
import model.LayeredImageModel;

public class MainRunner {

  public static void main(String[] args) {
    ImageProcessorLayerModel model = new LayeredImageModel();
    ImageProcessorController controller = new SimpleImageController(model, System.out);
    Readable in = new InputStreamReader(System.in);
    controller.run(in);

  }
}
