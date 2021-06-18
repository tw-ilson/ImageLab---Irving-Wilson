import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import model.AbstractImageProcessorModel;
import model.ImageProcessorIO;
import model.ImageProcessorModel;
import model.LayerImageModel;
import model.SimpleImageProcessorModel;
import model.filters.Filter;
import model.filters.FilterBuilder;
import model.image.Image;
import model.image.SimpleLayeredImage;

public class MainRunner {
  public static void main(String[] args) {
    ImageProcessorIO io = new SimpleImageProcessorModel();
    io.importImage("bay.ppm");
    try {
      io.export("jpeg", "bay_2.png");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
