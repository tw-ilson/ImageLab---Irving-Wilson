import controller.ImageGUIController;
import controller.ImageProcessorController;
import controller.SimpleImageController;
import java.io.InputStreamReader;
import model.ImageProcessorLayerModel;
import model.LayeredImageModel;

/**
 * Main.
 */
public class ImageProcessor {

  /**
   * Main.
   * @param args program arguments.
   */
  public static void main(String[] args) {
    ImageProcessorLayerModel model = new LayeredImageModel();
    ImageProcessorController controller = new ImageGUIController(model);
    controller.run();
  }
}
