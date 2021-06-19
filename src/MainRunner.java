import controller.SimpleImageController;
import java.io.IOException;
import controller.ImageUtils;
import javax.imageio.ImageIO;

public class MainRunner {
  public static void main(String[] args) {
    String[] formats = ImageIO.getWriterFormatNames();
    for (String s: formats) {
      System.out.println(s);
    }
  }
}
