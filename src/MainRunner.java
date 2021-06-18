import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import model.AbstractImageProcessorModel;
import model.ImageProcessorIO;
import model.ImageProcessorModel;
import model.SimpleImageProcessorModel;

public class MainRunner {
  public static void main(String[] args) {
    ImageProcessorIO io = new SimpleImageProcessorModel();
    io.importImage("photos/bay.ppm");
    try {
      io.export("png", "bay.png");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
