import java.io.FileNotFoundException;
import model.FileType;
import model.ImageProcessorModel;
import model.ImageUtil;
import model.SimpleImageProcessorModel;
import model.image.CheckerboardImage;

public class MainRunner {
  public static void main(String[] args) {
    String filename;
    ImageProcessorModel model = new SimpleImageProcessorModel();

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "sample.ppm";
    }

    try {
      model.importImage(new CheckerboardImage(2, 2));
    } catch (IllegalArgumentException e) {
      System.out.println("File "+filename+ " not found!");
    }

    model.export(FileType.PPM, "checker.ppm");
  }
}
