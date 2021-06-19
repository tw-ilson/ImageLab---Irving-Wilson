import controller.SimpleImageController;
import java.io.IOException;
import controller.ImageUtils;

public class MainRunner {
  public static void main(String[] args) {
    ImageUtils io = new SimpleImageController();
    io.importImage("bay.ppm");
    try {
      io.export("jpeg", "bay_2.png");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
