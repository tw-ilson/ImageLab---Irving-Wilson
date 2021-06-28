
import controller.ImageGUIController;
import controller.ImageProcessorController;
import controller.SimpleImageController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import model.ImageProcessorLayerModel;
import model.LayeredImageModel;

/**
 * Main.
 */
public class ImageProcessor {

  /**
   * Main.
   *
   * @param args program arguments.
   */
  public static void main(String[] args) {
    ImageProcessorLayerModel model = new LayeredImageModel();
    ImageProcessorController controller = null;
    if (args[0].equals("-interactive")) {
      controller = new ImageGUIController(model);
    } else if (args[0].equals("-text")) {
      controller = new SimpleImageController(model, new InputStreamReader(System.in), System.out);
    } else if (args[0].equals("-script")) {
      String batPath = args[1];
      try {
        controller = new SimpleImageController(model, new FileReader(new File(batPath)),
            System.out);
      } catch (FileNotFoundException e) {
        System.out.println("File not found. Quitting");
        System.exit(0);
      }
    } else {
      System.out.println("Invalid arguments. Quitting.");
      System.exit(0);
    }
    controller.run();
  }
}
