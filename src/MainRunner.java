import java.io.IOException;
import model.ImageProcessorIO;
import model.SimpleImageModel;

public class MainRunner {
  public static void main(String[] args) {
    ImageProcessorIO io = new SimpleImageModel();
    io.importImage("photos/bay.ppm");
    try {
      io.export("png", "bay.png");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
