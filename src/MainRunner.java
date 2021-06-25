import controller.ImageProcessorController;
import controller.SimpleImageController;
import java.io.InputStreamReader;
import model.ImageProcessorLayerModel;
import model.LayeredImageModel;
import view.JFrameView;

/**
 * Main.
 */
public class MainRunner {

  /**
   * Main.
   * @param args program arguments.
   */
  public static void main(String[] args) {
    ImageProcessorLayerModel model = new LayeredImageModel();
    /*Readable in = new InputStreamReader(System.in);
    ImageProcessorController controller = new SimpleImageController(model, in, System.out);
    controller.run();*/
    JFrameView view = new JFrameView(model);
  }
}
