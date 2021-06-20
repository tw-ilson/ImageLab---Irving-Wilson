import controller.ImageProcessorController;
import controller.ImageUtils;
import controller.SimpleImageController;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import model.ImageProcessorLayerModel;
import model.LayeredImageModel;
import model.image.CheckerboardImage;

public class MainRunner {
  public static void main(String[] args) {
    ImageProcessorLayerModel model = new LayeredImageModel();
    ImageProcessorController controller = new SimpleImageController(model, System.out);
//    try {
//      controller.run(new FileReader("example-run.txt"));
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    }
    try {
      ImageUtils.write("png", "chek.png", new CheckerboardImage(4, 7));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
